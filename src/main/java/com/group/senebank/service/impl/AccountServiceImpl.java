package com.group.senebank.service.impl;

import com.group.senebank.exception.NotFoundException;
import com.group.senebank.model.Account;
import com.group.senebank.repository.AccountRepository;
import com.group.senebank.service.AccountService;
import com.group.senebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static com.group.senebank.util.ErrorMessages.ACCOUNT_ERROR_MESSAGE;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    @Override
    public Account getAccountById(int id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ACCOUNT_ERROR_MESSAGE, id));
    }

    @Override
    public Account addAccount(UUID userId) {
        return accountRepository.save(
                Account.builder()
                        .isOverdraft(false)
                        .balance(0)
                        .user(userService.getUserById(userId))
                        .build()
        );
    }

    @Override
    public void closeAccount(int id) {
        if(!accountRepository.existsById(id)){
            throw new NotFoundException(ACCOUNT_ERROR_MESSAGE, id);
        }
        accountRepository.deleteById(id);
    }
}
