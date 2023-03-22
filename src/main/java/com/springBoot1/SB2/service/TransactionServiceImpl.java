package com.springBoot1.SB2.service;

import com.springBoot1.SB2.config.Authority.Authority;
import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.dto.transaction.*;
import com.springBoot1.SB2.entity.Medicine;
import com.springBoot1.SB2.entity.MedicineTransaction;
import com.springBoot1.SB2.entity.PharmacyMedicine;
import com.springBoot1.SB2.entity.Transaction;
import com.springBoot1.SB2.event.MedicineCountCheckingEvent;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.repository.MedicineRepository;
import com.springBoot1.SB2.repository.MedicineTransactionRepository;
import com.springBoot1.SB2.repository.PharmacyMedicineRepository;
import com.springBoot1.SB2.service.base.BaseTrashableCRUDServiceImpl;
import com.springBoot1.SB2.service.invoice.MedicineCountAndPriceTransactionInvoiceData;
import com.springBoot1.SB2.service.invoice.TransactionInvoiceData;
import com.springBoot1.SB2.service.invoice.TransactionInvoiceMaker;
import com.springBoot1.SB2.util.ArrayUtil;
import com.springBoot1.SB2.util.ResourceLoaderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
@Service
public class TransactionServiceImpl extends BaseTrashableCRUDServiceImpl<Transaction, Long, CreateTransactionDTO, ShowAllTransactionDTO, ShowTransactionDTO, UpdateTransactionDTO> {
    @Autowired
    private MedicineRepository medicineRepository;
    @Autowired
    private MedicineTransactionRepository medicineTransactionRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private ApplicationEventPublisher eventPublisher;
    @Autowired
    private PharmacyMedicineRepository pharmacyMedicineRepository;
    @Value("${notifications.medicineCountLimit}")
    private int medicineCountLimit;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private ResourceLoaderUtil loaderUtil;
    @Autowired
    private BaseMapper<Transaction, ShowTransactionDTO, ShowAllTransactionDTO, CreateTransactionDTO, UpdateTransactionDTO> baseMapper;
    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONCREATE + "')")
    @Override
    public void create(CreateTransactionDTO createTransactionDTO) throws ApiException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TransactionInvoiceData invoiceData =
                new TransactionInvoiceData();
        invoiceData.setPharmacistName(userDetails.getUser().getFname()+" "+
                userDetails.getUser().getLname());
        Set<Long> medicines=createTransactionDTO.getMedicines().stream()
                .map(CreateTransactionalMedicineDTO::getMedicine).collect(Collectors.toSet());
        List<Medicine> medicines1=medicineRepository.findAllById(medicines);
        Map<Long,Integer> medicineIdMap=new HashMap<>();
        createTransactionDTO.getMedicines().forEach(c -> {
            medicineIdMap.put(c.getMedicine(),c.getCount());
        });
        AtomicReference<Double> totPrice = new AtomicReference<>((double) 0);
        Set<MedicineTransaction> medicineTransactions = new HashSet<>();
        Transaction transaction=baseMapper.unMapCreated(createTransactionDTO);
        invoiceData.setCustomerName(transaction.getCustomer().getFname()+" "+transaction.getCustomer().getLname());
        invoiceData.setPharmacyName(transaction.getPharmacy().getName());
        invoiceData.setPharmacyAddress(transaction.getPharmacy().getAddress().getGovernorate()+"-"
        +transaction.getPharmacy().getAddress().getCity()+"-"
        +transaction.getPharmacy().getAddress().getTown());
        Set<PharmacyMedicine> pharmacyMedicines=new HashSet<>();
        medicines1.forEach(medicine -> {
            try {
                PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository
                        .findByPharmacyIdAndMedicineId(createTransactionDTO.getPharmacy(),medicine.getId())
                        .orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(createTransactionDTO.getPharmacy().toString()+" or medicine "+medicine.getId().toString()), LocaleContextHolder.getLocale())));
                if(pharmacyMedicine.getCount()<medicineIdMap.get(medicine.getId())){
                    throw  new ApiException(messageSource.getMessage("transactions.medicine.count.low", ArrayUtil.of(medicine.getId().toString()+"-"+medicine.getMarketName()), LocaleContextHolder.getLocale()));
                }
                pharmacyMedicines.add(pharmacyMedicine);
                medicineTransactions.add(MedicineTransaction.builder().transaction(transaction).medicine(medicine).count(medicineIdMap.get(medicine.getId())).build());
                pharmacyMedicine.setCount(pharmacyMedicine.getCount()-medicineIdMap.get(medicine.getId()));
//                trigger event MedicineCountCheckingEvent
                if(!pharmacyMedicine.isEmailed()&&pharmacyMedicine.getCount()<=medicineCountLimit){
                    System.out.println("======Event triggerd======");
                    MedicineCountCheckingEvent event = new MedicineCountCheckingEvent(pharmacyMedicine.getMedicine().getId(), pharmacyMedicine.getPharmacy().getId());
                    eventPublisher.publishEvent(event);
                    pharmacyMedicine.setEmailed(true);
                }
                invoiceData.getMedicines().add(new MedicineCountAndPriceTransactionInvoiceData(pharmacyMedicine.getMedicine().getMarketName(),medicineIdMap.get(medicine.getId()),pharmacyMedicine.getExpiration(),pharmacyMedicine.getMedicine().getPrice(),medicineIdMap.get(medicine.getId())*pharmacyMedicine.getMedicine().getPrice()));
                totPrice.updateAndGet(v -> v + medicineIdMap.get(medicine.getId()) * medicine.getPrice());
            } catch (ApiException e) {
                throw new RuntimeException(e);
            }
        });
        pharmacyMedicineRepository.saveAll(pharmacyMedicines);
        transaction.setMedicineTransactions(medicineTransactions);
        transaction.setPrice(totPrice.get());
        invoiceData.setTotPrice(totPrice.get());
        transaction.setInvoice("");
        Transaction savedTransaction = baseRepository.save(transaction);
        invoiceData.setTransactionId(savedTransaction.getId());
        try {
            String invoicePath = TransactionInvoiceMaker.make(invoiceData);
            savedTransaction.setInvoice(invoicePath);
            baseRepository.save(savedTransaction);
            loaderUtil.loadAndWriteFile(invoicePath,request,response);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONUPDATE + "')")
    @Override
    public void update(Long id, UpdateTransactionDTO updateTransactionDTO) throws ApiException {
        Transaction transaction = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        transaction=baseMapper.unMapUpdated(transaction,updateTransactionDTO);
        if (updateTransactionDTO.getMedicines()!=null&& !updateTransactionDTO.getMedicines().isEmpty()) {
            Map<Long,Integer> medicineTransMap=new LinkedHashMap<>();
            transaction.getMedicineTransactions().forEach(medicineTransaction -> {
                medicineTransMap.put(medicineTransaction.getMedicine().getId(),medicineTransaction.getCount());
            });
            List<PharmacyMedicine> pharmacyMedicines= pharmacyMedicineRepository.findByPharmacyId(transaction.getPharmacy().getId());
            pharmacyMedicines.forEach(pharmacyMedicine -> {
                        if(medicineTransMap.containsKey(pharmacyMedicine.getMedicine().getId())){
                            pharmacyMedicine.setCount(pharmacyMedicine.getCount()+medicineTransMap.get((pharmacyMedicine.getMedicine().getId())));
                        }
                    });
            pharmacyMedicineRepository.saveAll(pharmacyMedicines);
            medicineTransactionRepository.deleteByTransactionId(id);
            transaction.setPrice((double) 0);
            AtomicReference<Double> totPrice = new AtomicReference<>((double) 0);
            Set<MedicineTransaction> medicineTransactions = new HashSet<>();
            Set<Long> medicines = updateTransactionDTO.getMedicines().stream()
                    .map(CreateTransactionalMedicineDTO::getMedicine).collect(Collectors.toSet());
            List<Medicine> medicines1 = medicineRepository.findAllById(medicines);
            Map<Long, Integer> medicineIdMap = new HashMap<>();
            updateTransactionDTO.getMedicines().forEach(c -> {
                medicineIdMap.put(c.getMedicine(), c.getCount());
            });
            Transaction finalTransaction1 = transaction;
            Set<PharmacyMedicine> newPharmacyMedicines=new HashSet<>();
            medicines1.forEach(medicine -> {
                try {
                    PharmacyMedicine pharmacyMedicine = pharmacyMedicineRepository
                            .findByPharmacyIdAndMedicineId(finalTransaction1.getPharmacy().getId(),medicine.getId())
                            .orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(finalTransaction1.getPharmacy().getId().toString()+" or medicine "+medicine.getId().toString()), LocaleContextHolder.getLocale())));
                    if(pharmacyMedicine.getCount()<medicineIdMap.get(medicine.getId())){
                        throw  new ApiException(messageSource.getMessage("transactions.medicine.count.low", ArrayUtil.of(medicine.getId().toString()+"-"+medicine.getMarketName()), LocaleContextHolder.getLocale()));
                    }
                    pharmacyMedicines.add(pharmacyMedicine);
                    medicineTransactions.add(MedicineTransaction.builder().transaction(finalTransaction1).medicine(medicine).count(medicineIdMap.get(medicine.getId())).build());
                    pharmacyMedicine.setCount(pharmacyMedicine.getCount()-medicineIdMap.get(medicine.getId()));
//                trigger event MedicineCountCheckingEvent
                    if(!pharmacyMedicine.isEmailed()&&pharmacyMedicine.getCount()<=medicineCountLimit){
                        System.out.println("======Event triggerd======");
                        MedicineCountCheckingEvent event = new MedicineCountCheckingEvent(pharmacyMedicine.getMedicine().getId(), pharmacyMedicine.getPharmacy().getId());
                        eventPublisher.publishEvent(event);
                        pharmacyMedicine.setEmailed(true);
                    }
                    totPrice.updateAndGet(v -> v + medicineIdMap.get(medicine.getId()) * medicine.getPrice());
                } catch (ApiException e) {
                    throw new RuntimeException(e);
                }
            });
            pharmacyMedicineRepository.saveAll(pharmacyMedicines);
            transaction.setPrice(totPrice.get());
            transaction.setMedicineTransactions(medicineTransactions);
        }
        baseRepository.save(transaction);
    }

    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONSHOW + "')")
    @Override
    public ShowTransactionDTO show(Long id) throws ApiException, UnAuthorizedAccessException {
        return super.show(id);
    }

    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONSHOWALL + "')")
    @Override
    public List<ShowAllTransactionDTO> findAll(int page, int size, String sort) {
       return super.findAll(page, size, sort);
    }

    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONDELETE + "')")
    @Override
    public void delete(Long id) throws UnAuthorizedAccessException, ApiException {
        Transaction transaction = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        try {
            Files.deleteIfExists(Paths.get(transaction.getInvoice()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        baseRepository.delete(transaction);
    }
    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONTRASH + "')")
    @Override
    public void trash(Long id) throws ApiException {
        Transaction transaction = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        transaction.setTrashed(true);
        baseRepository.save(transaction);
    }

    @PreAuthorize("hasAuthority('" + Authority.TRANSACTIONRESTORE + "')")
    @Override
    public void restore(Long id) throws ApiException {
        Transaction transaction = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        transaction.setTrashed(false);
        baseRepository.save(transaction);
    }
}
