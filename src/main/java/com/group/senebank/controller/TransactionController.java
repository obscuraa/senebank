package com.group.senebank.controller;

import com.group.senebank.dto.transaction.FullTransactionDto;
import com.group.senebank.mapper.TransactionMapper;
import com.group.senebank.model.Transaction;
import com.group.senebank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

//    @GetMapping
//    public List<Transaction> getTransactions() {
//        return transactionMapper.toListTransactionDto(transactionService.getTransactions());
//    }

//    @GetMapping(path = "/{userId}")
//    public FullTransactionDto findTransactionsByUserId(@PathVariable("userId") String userId) {
//        return transactionMapper.toFullDto(transactionService.getTransactionsByUserId(userId));
//    }
//
//    @GetMapping(path = "/{accountId}")
//    public FullTransactionDto findTransactionsByAccountId(@PathVariable("accountId") int accountId) {
//        return transactionMapper.toFullDto(transactionService.getTransactionsByAccountId(accountId));
//    }
    @PostMapping
    public FullTransactionDto sendTransaction(@RequestParam int sourceId, @RequestParam int targetId, @RequestParam int cash){
        return transactionMapper.toFullDto(transactionService.sendTransaction(sourceId, targetId, cash));
    }
}
