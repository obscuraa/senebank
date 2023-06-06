package com.group.senebank.dao;

import com.group.senebank.exception.NotFoundException;
import com.group.senebank.model.Account;
import com.group.senebank.model.User;
import com.group.senebank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.group.senebank.util.ErrorMessages.ACCOUNT_ERROR_MESSAGE;

@Component
@RequiredArgsConstructor
public class AccountDao {

    private final AccountRepository accountRepository;

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public List<Account> findAllByUser(User user) {
        return accountRepository.findAllByUser(user);
    }

    public Account findById(int id) {
        return accountRepository.findById(id).orElseThrow(() -> new NotFoundException(ACCOUNT_ERROR_MESSAGE, id));
    }

    public void deleteById(int id) {
        accountRepository.deleteById(id);
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}
