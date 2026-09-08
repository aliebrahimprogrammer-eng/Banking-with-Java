package com.bank.models;

public class SavingsAccount extends Account {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if(amount <= getBalance()){
            setBalance(getBalance()-amount);
            addTransaction(new Transaction(amount,"Withdraw"));
        }
    }
}
