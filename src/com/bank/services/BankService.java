package com.bank.services;

import com.bank.interfaces.ITransactionOperations;
import com.bank.models.Account;

import java.util.ArrayList;
import java.util.List;

public class BankService implements ITransactionOperations {

    private List<Account> accounts;

    public BankService(){
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    private Account findAccount(String accountNumber) {
        for (Account account : accounts){
            if (account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null){
            account.deposit(amount);
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null){
            account.withdraw(amount);
        }
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);
        if (fromAccount != null && toAccount != null){
            fromAccount.withdraw(amount);
            toAccount.deposit(amount);
        }

    }
}
