package com.bank.models;

import java.time.LocalDateTime;

public class Transaction {

    private double amount;
    private String type;
    private LocalDateTime date;

    public Transaction(double amount, String type) {
        this.amount = amount;
        this.type = type;
        this.date = LocalDateTime.now();
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

}
