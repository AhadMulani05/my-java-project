package com.example.TransactionsDemo.service;

import com.example.TransactionsDemo.entity.Account;
import com.example.TransactionsDemo.repository.AccountRespository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AccountService {

    private AccountRespository accountRespository;

    public AccountService(AccountRespository accountRespository) {
        this.accountRespository = accountRespository;
    }

    public void createAccount(Account account) {
        accountRespository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRespository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acount not found" + id));
    }

    public void deleteAccount(Long id) {
        accountRespository.deleteById(id);
    }

}
