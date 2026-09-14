package com.bank.models;

public class DebitCard {

    String cardNumber;
    String cardType;

    private double withdrawLimit;
    private double transferLimit;
    private double ownAccountTransferLimit;
    private double depositLimit;
    private double ownAccountDepositLimit;

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


}
