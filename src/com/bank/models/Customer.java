package com.bank.models;

public class Customer {

    //customer properties
    private String customerId;
    private String name;
    private CheckingAccount checkingAccount;
    private SavingsAccount savingsAccount;

    //constructor
    public Customer(String customerId,String name){
        this.customerId = customerId;
        this.name = name;
    }

    //getters and setters
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
