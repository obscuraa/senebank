package com.group.senebank.controller;

import com.group.senebank.dto.transaction.FullTransactionDto;
import com.group.senebank.mapper.TransactionMapper;
import com.group.senebank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    public FullTransactionDto sendTransaction(@RequestParam int sourceAccountId,
                                              @RequestParam int targetAccountId,
                                              @RequestParam int cash) {
        return transactionMapper.toFullDto(transactionService.sendTransaction(sourceAccountId, targetAccountId, cash));
    }
}
