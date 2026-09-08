package com.bank;

import com.bank.models.*;
import com.bank.services.BankService;

public class Main {
    public static void main(String[] args){

        BankService bankService = new BankService();

        Customer customer = new Customer("123456789D","Ali Ebrahim","password123");
        Banker banker = new Banker("897645321","Ahmed Hani","qwerty123");
        CheckingAccount cAccount = new CheckingAccount("54321",300);
        SavingsAccount sAccount = new SavingsAccount("12345",200);

        customer.setCheckingAccount(cAccount);
        customer.setSavingsAccount(sAccount);
        bankService.addAccount(cAccount);
        bankService.addAccount(sAccount);

        System.out.println(customer.getName());
        System.out.println(customer.getCustomerId());
        System.out.println(sAccount.getBalance());
        System.out.println(cAccount.getBalance());

        /*customer.getCheckingAccount().withdraw(10);
        customer.getCheckingAccount().withdraw(50);
        customer.getCheckingAccount().withdraw(40);
        customer.getCheckingAccount().withdraw(20);
        customer.getCheckingAccount().deposit(100);
        customer.getCheckingAccount().deposit(1000);
        System.out.println(customer.getCheckingAccount().getBalance());
        customer.getSavingsAccount().deposit(20);
        System.out.println(customer.getSavingsAccount().getBalance());
        System.out.println(customer.getSavingsAccount().getBalance() + customer.getCheckingAccount().getBalance());
        */
        System.out.println("-----------------------------------");
        bankService.withdraw(cAccount.getAccountNumber(),-40);
        System.out.println(cAccount.getBalance());
        System.out.println("-----------------------------------");
        bankService.withdraw(cAccount.getAccountNumber(),400);
        System.out.println(cAccount.getBalance());
        System.out.println("-----------------------------------");
        bankService.withdraw(cAccount.getAccountNumber(),500);
        System.out.println(cAccount.getBalance());
        System.out.println("-----------------------------------");
        bankService.withdraw(cAccount.getAccountNumber(),10);
        System.out.println(cAccount.getBalance());
        System.out.println("-----------------------------------");
        bankService.deposit(cAccount.getAccountNumber(),590);
        bankService.deposit(cAccount.getAccountNumber(),10);
        System.out.println("-----------------------------------");
        System.out.println(cAccount.getBalance());
        System.out.println("-----------------------------------");
        bankService.transfer(cAccount.getAccountNumber(),sAccount.getAccountNumber(),10);
        System.out.println(cAccount.getBalance());
        System.out.println(sAccount.getBalance());
        System.out.println("-----------------------------------");


        for (Transaction transaction : customer.getCheckingAccount().getTransactionsList()){
            System.out.println(transaction.getAmount());
            System.out.println(transaction.getType());
            System.out.println(transaction.getDate());
        }

    }
}
