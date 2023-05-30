package com.group.senebank.controller;

import com.group.senebank.dto.account.FullAccountDto;
import com.group.senebank.mapper.AccountMapper;
import com.group.senebank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(path = "/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final AccountMapper accountMapper;

    @DeleteMapping(path = "/{accountId}")
    public void closeAccount(@PathVariable("accountId") int id) {
        accountService.closeAccount(id);
    }

    @GetMapping(path = "/{accountId}")
    public FullAccountDto getAccount(@PathVariable("accountId") int id){
        return accountMapper.toFullDto(accountService.getAccountById(id));
    }

    @PostMapping(path = "/{userId}")
    public FullAccountDto openAccount(@PathVariable("userId") UUID userId){
        return accountMapper.toFullDto(accountService.addAccount(userId));
    }
}
