package com.group.senebank.service.impl;

import com.group.senebank.dao.TransactionDao;
import com.group.senebank.dto.transaction.CreateTransactionDto;
import com.group.senebank.exception.SendTransactionException;
import com.group.senebank.model.Transaction;
import com.group.senebank.service.AccountService;
import com.group.senebank.service.TransactionService;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static com.group.senebank.util.ErrorMessages.ACCOUNT_SEND_TRANSACTION_ERROR_MESSAGE;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionServiceImpl implements TransactionService {
    private final TransactionDao transactionDao;
    private final UserService userService;
    private final AccountService accountService;

    @Override
    public List<Transaction> getTransactions() {
        return transactionDao.findAll();
    }

    @Override
    public List<Transaction> getTransactionsByUserId(UUID userId) {
        return transactionDao.findAllTransactionsByUser(userService.getUserById(userId));
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int id) {
        return transactionDao.getTransactionsByAccount(accountService.getAccountById(id));
    }

    @Override
    public Transaction sendTransaction(CreateTransactionDto createTransactionDto) {
        var sourceAccount = accountService.getAccountById(createTransactionDto.getSourceAccountId());
        var targetAccount = accountService.getAccountById(createTransactionDto.getTargetAccountId());

        int cash = createTransactionDto.getCash();

        if(!sourceAccount.isOverdraft() && sourceAccount.getBalance() >= cash){
            sourceAccount.setBalance(sourceAccount.getBalance() - cash);
            targetAccount.setBalance(targetAccount.getBalance() + cash);
            return transactionDao.saveTransaction(
                    Transaction.builder()
                            .targetAccount(targetAccount)
                            .sourceAccount(sourceAccount)
                            .payload(String.valueOf(cash))
                            .user(sourceAccount.getUser())
                            .build()
            );
        }
        throw new SendTransactionException(ACCOUNT_SEND_TRANSACTION_ERROR_MESSAGE, createTransactionDto.getSourceAccountId());
    }
}
