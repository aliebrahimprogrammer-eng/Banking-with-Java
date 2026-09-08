package com.bank.models;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private final String accountNumber;
    private double balance;
    private List<Transaction> transactionsList;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionsList = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactionsList() {
        return transactionsList;
    }

    public abstract void withdraw(double amount);

    public void deposit(double amount){
        balance += amount;
        addTransaction(new Transaction(amount,"Deposit"));
    }

    public void addTransaction(Transaction transaction){
        transactionsList.add(transaction);
    }



}
