package com.example.TransactionsDemo.controller;

import com.example.TransactionsDemo.entity.Account;
import com.example.TransactionsDemo.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping ("/api/account")
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(@RequestBody Account account) {
        accountService.createAccount(account);

        return ResponseEntity.ok("Done");
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);

        return ResponseEntity.ok(account);
    }


    @DeleteMapping ("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {

        accountService.deleteAccount(id);

        return ResponseEntity.ok("Done");
    }

}
