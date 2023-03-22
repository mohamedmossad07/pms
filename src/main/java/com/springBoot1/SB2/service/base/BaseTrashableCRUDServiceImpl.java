package com.springBoot1.SB2.service.base;

import com.springBoot1.SB2.config.UserDetailsImpl;
import com.springBoot1.SB2.entity.Address;
import com.springBoot1.SB2.exception.api.ApiException;
import com.springBoot1.SB2.exception.api.UnAuthorizedAccessException;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.repository.base.BaseRepository;
import com.springBoot1.SB2.util.ArrayUtil;
import com.springBoot1.SB2.util.ObjectUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
public abstract class BaseTrashableCRUDServiceImpl<T, ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO> implements TrashableCRUDService<ID, CreateDTO, ViewAllDTO, ViewDTO, UpdateDTO> {
    @Autowired
    protected BaseRepository<T, ID> baseRepository;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private T t;
    @Autowired
    private BaseMapper<T,ViewDTO,ViewAllDTO,CreateDTO,UpdateDTO> baseMapper;
    @Override
    public void create(CreateDTO createDTO) throws  UnAuthorizedAccessException, ApiException {
        instantiateT();
        baseRepository.save(baseMapper.unMapCreated(createDTO));
    }

    @Override
    public void update(ID id, UpdateDTO updateDTO) throws  UnAuthorizedAccessException, ApiException {
        T l = baseRepository.findById(id).orElseThrow(() -> new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        baseRepository.save(baseMapper.unMapUpdated(l,updateDTO));
    }

    @Override
    public void delete(ID id) throws  UnAuthorizedAccessException, ApiException {
        baseRepository.findById(id).orElseThrow(() ->  new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        baseRepository.deleteById(id);
    }

    @Override
    public List<ViewAllDTO> findAll(int page, int size, String sort){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<T> data = baseRepository.findAll(pageable);
        return baseMapper.mapToShowAll(data.getContent());
    };

    private void instantiateT() {
        try {
            t = (T) t.getClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ViewDTO show(ID id) throws  UnAuthorizedAccessException, ApiException {
        T e = baseRepository.findById(id).orElseThrow(() ->
                new ApiException(messageSource.getMessage("exceptions.id.notfound", ArrayUtil.of(id.toString()), LocaleContextHolder.getLocale())));
        return baseMapper.mapToShow(e);
//    return null;
    }

    protected void checkPriority(Short p) throws UnAuthorizedAccessException {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (p < userDetails.getPriority()) {
            throw new UnAuthorizedAccessException(messageSource.getMessage("exceptions.priority.low", ArrayUtil.of(), LocaleContextHolder.getLocale()));
        }
    }
}
