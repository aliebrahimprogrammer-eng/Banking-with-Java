package com.bank.models;

public class Customer extends User {

    //customer properties
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    //constructor
    public Customer(String customerId, String name, String password) {
        super(customerId, name, password);
    }

    //getters and setters
    public CheckingAccount getCheckingAccount() {
        return checkingAccount;
    }

    public void setCheckingAccount(CheckingAccount checkingAccount) {
        this.checkingAccount = checkingAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount savingsAccount) {
        this.savingsAccount = savingsAccount;
    }


}
