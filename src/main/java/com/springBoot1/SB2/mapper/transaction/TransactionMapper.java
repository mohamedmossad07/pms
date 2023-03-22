package com.springBoot1.SB2.mapper.transaction;

import com.springBoot1.SB2.dto.supplier.CreateSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowAllSupplierDTO;
import com.springBoot1.SB2.dto.supplier.ShowSupplierDTO;
import com.springBoot1.SB2.dto.supplier.UpdateSupplierDTO;
import com.springBoot1.SB2.dto.transaction.CreateTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowAllTransactionDTO;
import com.springBoot1.SB2.dto.transaction.ShowTransactionDTO;
import com.springBoot1.SB2.dto.transaction.UpdateTransactionDTO;
import com.springBoot1.SB2.entity.Supplier;
import com.springBoot1.SB2.entity.Transaction;
import com.springBoot1.SB2.mapper.address.AddressMapper;
import com.springBoot1.SB2.mapper.base.BaseMapper;
import com.springBoot1.SB2.mapper.base.IdsMapper;
import com.springBoot1.SB2.mapper.customer.CustomerMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(uses = {IdsMapper.class, CustomerMapper.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TransactionMapper extends BaseMapper<Transaction, ShowTransactionDTO, ShowAllTransactionDTO, CreateTransactionDTO, UpdateTransactionDTO> {
    @Override
    @Mapping(target = "medicines",source = "medicineTransactions")
    ShowTransactionDTO mapToShow(Transaction transaction);
}