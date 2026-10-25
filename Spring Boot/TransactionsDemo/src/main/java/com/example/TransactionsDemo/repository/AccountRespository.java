package com.example.TransactionsDemo.repository;

import com.example.TransactionsDemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRespository extends JpaRepository<Account, Long> {

}
