package com.group.senebank.service.impl;

import com.group.senebank.model.Account;
import com.group.senebank.model.User;
import com.group.senebank.repository.AccountRepository;
import com.group.senebank.service.AccountService;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public Account getAccount(int id) {
        return accountRepository.findById(id).orElseThrow(() -> new IllegalStateException("Account not found by ID"));
    }

    @Override
    public Account addAccount(UUID userId) {
        User user = userService.findUserById(userId);
        Account account = new Account();
        account.setOverdraft(false);
        account.setBalance(0);
        account.setUser(user);
        return accountRepository.save(account);
    }

    @Override
    public void deleteAccount(int id) {
        if(!accountRepository.existsById(id)){
            throw new IllegalStateException("Account id " + id + "not found");
        }
        accountRepository.deleteById(id);
    }
}
