package com.group.senebank.dao;

import com.group.senebank.exception.NotFoundException;
import com.group.senebank.model.Account;
import com.group.senebank.model.Transaction;
import com.group.senebank.model.User;
import com.group.senebank.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.group.senebank.util.ErrorMessages.TRANSACTION_ERROR_MESSAGE;

@Component
@RequiredArgsConstructor
public class TransactionDao {

    private final TransactionRepository transactionRepository;

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction findTransactionById(int id) {
        return transactionRepository.findById(id).orElseThrow(() -> new NotFoundException(TRANSACTION_ERROR_MESSAGE, id));
    }

    public void deleteById(int id) {
        transactionRepository.deleteById(id);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public List<Transaction> findAllTransactionsByUser(User user) {
        return transactionRepository.findAllByUser(user);
    }

    public List<Transaction> getTransactionsByAccount(Account account) {
        return transactionRepository.findAllBySourceAccount(account);
    }
}
