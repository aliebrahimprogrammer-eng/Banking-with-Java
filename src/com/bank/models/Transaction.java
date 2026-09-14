package com.bank.models;

import java.time.LocalDateTime;

public class Transaction {

    private double amount;
    private String type;
    private LocalDateTime date;
    private double balanceAfter;

    public Transaction(double amount, String type, double balanceAfter) {
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
        this.balanceAfter = balanceAfter;
    }

    public Transaction(double amount, String type, double balanceAfter, LocalDateTime date) {
        this.amount = amount;
        this.type = type;
        this.balanceAfter = balanceAfter;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }
}
