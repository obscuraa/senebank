package com.group.senebank.controller;

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

    @DeleteMapping(path = "{accountId}")
    public void deleteAccount(@PathVariable("accountId")int id) {
        accountService.deleteAccount(id);
    }

    @GetMapping(path = "/{accountId}")
    public void getAccount(@PathVariable("accountId")int id){
        accountService.getAccount(id);
    }

    @PostMapping(path = "/{userId}")
    public void openAccount(@PathVariable("userId") UUID userId){
        accountService.addAccount(userId);
    }
}
