package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Transaction;

import java.util.List;

public interface ITransactionDAO {
    void insertTransaction(Transaction transaction);
    List<Transaction> getAllTransactions();
}
