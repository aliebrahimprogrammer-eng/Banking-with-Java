package com.bank.models;

public class CheckingAccount extends Account{

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw(double amount) {
        if(amount <= getBalance()){
            setBalance(getBalance()-amount);
        }
    }
}
