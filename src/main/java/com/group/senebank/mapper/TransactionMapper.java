package com.group.senebank.mapper;

import com.group.senebank.dto.transaction.FullTransactionDto;
import com.group.senebank.model.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    FullTransactionDto toFullDto(Transaction transaction);

    List<FullTransactionDto> toFullDtoList(List<Transaction> transactions);
}
