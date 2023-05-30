package com.group.senebank.service.impl;

import com.group.senebank.exception.SendTransactionException;
import com.group.senebank.model.Transaction;
import com.group.senebank.repository.TransactionRepository;
import com.group.senebank.service.AccountService;
import com.group.senebank.service.TransactionService;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.group.senebank.config.ErrorMessages.ACCOUNT_SEND_TRANSACTION_ERROR_MESSAGE;

@Service
@RequiredArgsConstructor
@Transactional
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
        return transactionRepository.findAllByUser(userService.getUserById(userId));
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int id) {
        return transactionRepository.findAllBySourceAccount(accountService.getAccountById(id));
    }

    @Override
    public Transaction sendTransaction(int sourceId, int targetId, int cash) {
        var sourceAccount = accountService.getAccountById(sourceId);
        var targetAccount = accountService.getAccountById(targetId);

        if(!sourceAccount.isOverdraft() && sourceAccount.getBalance() >= cash){
            sourceAccount.setBalance(sourceAccount.getBalance() - cash);
            targetAccount.setBalance(targetAccount.getBalance() + cash);
            return transactionRepository.save(
                    Transaction.builder()
                            .targetAccount(targetAccount)
                            .sourceAccount(sourceAccount)
                            .payload(String.valueOf(cash))
                            .user(sourceAccount.getUser())
                            .build()
            );
        }
        throw new SendTransactionException(ACCOUNT_SEND_TRANSACTION_ERROR_MESSAGE, sourceId);
    }
}
