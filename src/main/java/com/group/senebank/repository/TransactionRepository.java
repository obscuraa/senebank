package com.group.senebank.repository;

import com.group.senebank.model.Account;
import com.group.senebank.model.Transaction;
import com.group.senebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByUser(User user);
    List<Transaction> findAllBySourceAccount(Account account);
}
