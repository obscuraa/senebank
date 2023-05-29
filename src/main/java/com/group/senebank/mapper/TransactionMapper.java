package com.group.senebank.mapper;

import com.group.senebank.dto.transaction.FullTransactionDto;
import com.group.senebank.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    List<Transaction> toListTransactionDto(List<Transaction> transactions);

    FullTransactionDto toFullDto(Transaction transactions);
}
