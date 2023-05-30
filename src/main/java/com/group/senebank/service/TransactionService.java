package com.group.senebank.service;

import com.group.senebank.dto.transaction.CreateTransactionDto;
import com.group.senebank.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> getTransactions();

    List<Transaction> getTransactionsByUserId(UUID id);

    List<Transaction> getTransactionsByAccountId(int id);

    Transaction sendTransaction(CreateTransactionDto createTransactionDto);
}
