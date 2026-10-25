package com.example.TransactionsDemo.repository;

import com.example.TransactionsDemo.entity.TransferRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<TransferRecord, Long> {

}
