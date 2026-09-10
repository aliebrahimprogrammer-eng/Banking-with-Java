package com.bank.models;

import com.bank.exceptions.AccountInactiveException;
import com.bank.exceptions.InsufficientFundException;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    private final String accountNumber;
    private double balance;
    private List<Transaction> transactionsList;
    private boolean active;
    private int overdraftCount;
    private String customerId;


    public Account(String customerId, String accountNumber, double balance) {
        this.customerId =customerId;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.transactionsList = new ArrayList<>();
        this.active = true;
        this.overdraftCount = 0;
    }

    public String getCustomerId() {
        return customerId;
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

    public boolean isActive(){
        return active;
    }

    public int getOverdraftCount(){
        return overdraftCount;
    }

    protected void setActive(boolean active){
        this.active = active;
    }

    protected void increaseOverdraftCount(){
        overdraftCount++;
    }

    public abstract void withdraw(double amount) throws InsufficientFundException,AccountInactiveException;

    public void deposit(double amount){
        balance += amount;
        addTransaction(new Transaction(amount,"Deposit"));
    }

    public void addTransaction(Transaction transaction){
        transactionsList.add(transaction);
    }

    public void processOverdraft(double amount) throws InsufficientFundException{
        double overdraftFee = 35;
        boolean isNegative = getBalance() >= 0 ? false : true;

        if (!isNegative) {
            setBalance( getBalance() - amount - overdraftFee);
            increaseOverdraftCount();
            addTransaction(new Transaction(amount,"Overdraft"));
            addTransaction(new Transaction(overdraftFee,"Overdraft Fee"));

            if(getOverdraftCount() >= 2){
                setActive(false);
            }
        }else{
            if (amount > 100){
                throw new InsufficientFundException("Your balance is already negative, amount must not be more than 100$.");
            }else{
                setBalance( getBalance() - amount - overdraftFee);
                increaseOverdraftCount();
                addTransaction(new Transaction(amount,"Overdraft"));
                addTransaction(new Transaction(overdraftFee,"Overdraft Fee"));

                if(getOverdraftCount() >= 2){
                    setActive(false);
                }
            }
        }

    }

}
