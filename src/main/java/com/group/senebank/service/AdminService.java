package com.group.senebank.service;

import com.group.senebank.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionByUserId(UUID userId);
    List<Transaction> getTransactionByAccountId(int accountId);
}
