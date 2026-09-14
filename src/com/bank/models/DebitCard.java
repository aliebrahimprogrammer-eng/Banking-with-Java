package com.bank.models;

public class DebitCard {

    String cardNumber;
    String cardType;

    private double withdrawLimit;
    private double transferLimit;
    private double ownAccountTransferLimit;
    private double depositLimit;
    private double ownAccountDepositLimit;

    private double dailyWithdrawAmount;
    private double dailyTransferAmount;
    private double dailyOwnAccountTransferAmount;
    private double dailyDepositAmount;
    private double dailyOwnAccountDepositAmount;

    public DebitCard(String cardNumber, String cardType) {
        this.cardNumber = cardNumber;
        this.cardType = cardType;

        if(cardType.equals("Mastercard Platinum")){
            withdrawLimit = 20000;
            transferLimit = 40000;
            ownAccountTransferLimit = 80000;
            depositLimit = 100000;
            ownAccountDepositLimit = 200000;
        }else if (cardType.equals("Mastercard Titanium")){
            withdrawLimit = 10000;
            transferLimit = 20000;
            ownAccountTransferLimit = 40000;
            depositLimit = 100000;
            ownAccountDepositLimit = 200000;
        }else {
            withdrawLimit = 5000;
            transferLimit = 10000;
            ownAccountTransferLimit = 20000;
            depositLimit = 100000;
            ownAccountDepositLimit = 200000;
        }

        this.dailyWithdrawAmount = 0;
        this.dailyTransferAmount = 0;
        this.dailyOwnAccountTransferAmount = 0;
        this.dailyDepositAmount = 0;
        this.dailyOwnAccountDepositAmount = 0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardType() {
        return cardType;
    }

    public double getWithdrawLimit() {
        return withdrawLimit;
    }

    public double getTransferLimit() {
        return transferLimit;
    }

    public double getOwnAccountTransferLimit() {
        return ownAccountTransferLimit;
    }

    public double getDepositLimit() {
        return depositLimit;
    }

    public double getOwnAccountDepositLimit() {
        return ownAccountDepositLimit;
    }

    public double getDailyWithdrawAmount() {
        return dailyWithdrawAmount;
    }

    public double getDailyTransferAmount() {
        return dailyTransferAmount;
    }

    public double getDailyOwnAccountTransferAmount() {
        return dailyOwnAccountTransferAmount;
    }

    public double getDailyDepositAmount() {
        return dailyDepositAmount;
    }

    public double getDailyOwnAccountDepositAmount() {
        return dailyOwnAccountDepositAmount;
    }

    public boolean canWithdraw (double amount){
        return dailyWithdrawAmount + amount <= withdrawLimit;
    }

    public boolean canTransfer (double amount){
        return dailyTransferAmount + amount <= transferLimit;
    }

    public boolean canTransferToOwnAccount (double amount){
        return dailyOwnAccountTransferAmount + amount <= ownAccountTransferLimit;
    }

    public boolean canDeposit (double amount){
        return dailyDepositAmount + amount <= depositLimit;
    }

    public boolean canDepositToOwnAccount (double amount){
        return dailyOwnAccountDepositAmount + amount <= ownAccountDepositLimit;
    }

    public void addWithdrawDailyUsage(double amount){
        dailyWithdrawAmount += amount;
    }

    public void addTransferDailyUsage(double amount){
        dailyTransferAmount += amount;
    }

    public void addDepositDailyUsage(double amount){
        dailyDepositAmount += amount;
    }

    public void addOwnAccountDepositDailyUsage(double amount){
        dailyOwnAccountDepositAmount += amount;
    }

    public void addOwnAccountTransferDailyUsage(double amount){
        dailyOwnAccountTransferAmount += amount;
    }
}
