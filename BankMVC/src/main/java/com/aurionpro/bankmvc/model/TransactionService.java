package com.aurionpro.bankmvc.model;

import com.aurionpro.bankmvc.entity.Transaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionService {
    TransactionDAO transactionDAO;



    public TransactionService(TransactionDAO transactionDAO) {
        this.transactionDAO = transactionDAO;
    }


    public void insertTransaction(Transaction transaction){
        this.transactionDAO.insertTransaction(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionDAO.getAllTransactions();
    }

    public List<Transaction> getAllTransactionsOfAccountNumber(Integer accountNumber){
        List<Transaction> first =transactionDAO.getAllTransactions().stream().filter(transaction -> (accountNumber.equals(transaction.getSenderAccountNumber()))).collect(Collectors.toList());
        List<Transaction> second =transactionDAO.getAllTransactions().stream().filter(transaction -> (accountNumber.equals(transaction.getReceiverAccountNumber()))).collect(Collectors.toList());
        first.addAll(second);
        return first;
    }
}
