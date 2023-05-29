package com.group.senebank.service.impl;

import com.group.senebank.model.Transaction;
import com.group.senebank.service.AdminService;
import com.group.senebank.service.TransactionService;
import com.group.senebank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    UserService userService;
    TransactionService transactionService;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @Override
    public List<Transaction> getTransactionByUserId(UUID userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @Override
    public List<Transaction> getTransactionByAccountId(int accountId) {
        return transactionService.getTransactionsByAccountId(accountId);
    }
}
