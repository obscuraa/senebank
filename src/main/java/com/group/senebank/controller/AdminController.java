package com.group.senebank.controller;

import com.group.senebank.dto.transaction.FullTransactionDto;
import com.group.senebank.mapper.TransactionMapper;
import com.group.senebank.model.Transaction;
import com.group.senebank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "/admin")
@RequiredArgsConstructor
public class AdminController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping
    public List<Transaction> getTransactions() {
        return transactionMapper.toListTransactionDto(transactionService.getTransactions());
    }

    @GetMapping(path = "/{userId}")
    public FullTransactionDto findTransactionsByUserId(@PathVariable("userId") UUID userId) {
        return transactionMapper.toFullDto((Transaction) transactionService.getTransactionsByUserId(userId));
    }

    @GetMapping(path = "/{accountId}")
    public FullTransactionDto findTransactionsByAccountId(@PathVariable("accountId") int accountId) {
        return transactionMapper.toFullDto((Transaction) transactionService.getTransactionsByAccountId(accountId));
    }
}
