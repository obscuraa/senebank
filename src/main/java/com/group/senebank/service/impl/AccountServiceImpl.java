package com.group.senebank.service.impl;

import com.group.senebank.dao.AccountDao;
import com.group.senebank.model.Account;
import com.group.senebank.service.AccountService;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;
    private final UserService userService;

    @Override
    public Account getAccountById(int id) {
        return accountDao.findById(id);
    }

    @Override
    public Account addAccount(UUID userId) {
        return accountDao.saveAccount(
                Account.builder()
                        .isOverdraft(false)
                        .balance(0)
                        .user(userService.getUserById(userId))
                        .build()
        );
    }

    @Override
    public void closeAccount(int id) {
        accountDao.deleteById(id);
    }
}
