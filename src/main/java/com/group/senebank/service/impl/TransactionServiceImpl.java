package com.group.senebank.service.impl;

import com.group.senebank.model.Account;
import com.group.senebank.model.Transaction;
import com.group.senebank.model.User;
import com.group.senebank.repository.TransactionRepository;
import com.group.senebank.service.AccountService;
import com.group.senebank.service.TransactionService;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final AccountService accountService;

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByUserId(UUID userId) {
        User user = userService.findUserById(userId);
        return transactionRepository.findAllByUser(user);
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int id) {
        Account account = accountService.getAccount(id);
        return transactionRepository.findAllBySourceAccount(account);
    }

    @Override
    public Transaction sendTransaction(int sourceId, int targetId, int cash) {
        Account sourceAccount = accountService.getAccount(sourceId);
        Account targetAccount = accountService.getAccount(targetId);

        if(!sourceAccount.isOverdraft() && sourceAccount.getBalance() >= cash){
            sourceAccount.setBalance(sourceAccount.getBalance() - cash);
            targetAccount.setBalance(targetAccount.getBalance() + cash);
            Transaction transaction = new Transaction();

            transaction.setSourceAccount(sourceAccount);
            transaction.setTargetAccount(targetAccount);
            transaction.setPayload(String.valueOf(cash));
            transaction.setUser(sourceAccount.getUser());

            return transactionRepository.save(transaction);
        }
        throw new RuntimeException("Account balance is not enough to complete transaction");
    }
}
