package com.example.TransactionsDemo.service;

import com.example.TransactionsDemo.entity.Account;
import com.example.TransactionsDemo.entity.TransferRecord;
import com.example.TransactionsDemo.repository.AccountRespository;
import com.example.TransactionsDemo.repository.TransferRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class TransferService {

    private AccountRespository accountRespository;
    private TransferRepository transferRepository;

    public TransferService(TransferRepository transferRepository,
                           AccountRespository accountRespository) {
        this.transferRepository = transferRepository;
        this.accountRespository = accountRespository;
    }

    @Transactional
    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount) {

        Account fromAccount = accountRespository.findById(fromAccountId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        Account toAccount = accountRespository.findById(toAccountId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        fromAccount.debitAccount(amount);
        accountRespository.saveAndFlush(fromAccount);

        toAccount.creditAccount(amount);
        accountRespository.saveAndFlush(toAccount);

        transferRepository.save(new TransferRecord(
                fromAccountId,
                toAccountId,
                amount,
                LocalDate.now()
        ));
        transferRepository.flush();

    }



}
