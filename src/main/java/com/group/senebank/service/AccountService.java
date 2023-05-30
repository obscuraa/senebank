package com.group.senebank.service;

import com.group.senebank.model.Account;

import java.util.UUID;

public interface AccountService {
    Account getAccountById(int id);

    Account addAccount(UUID userId);

    void deleteAccount(int id);
}
