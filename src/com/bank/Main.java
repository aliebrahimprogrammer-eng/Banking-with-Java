package com.bank;

import com.bank.models.CheckingAccount;
import com.bank.models.Customer;
import com.bank.models.SavingsAccount;
import com.bank.models.Transaction;

public class Main {
    public static void main(String[] args){
        Customer customer = new Customer("123456789D","Ali Ebrahim");
        CheckingAccount cAccount = new CheckingAccount("54321",300);
        SavingsAccount sAccount = new SavingsAccount("12345",200);

        customer.setCheckingAccount(cAccount);
        customer.setSavingsAccount(sAccount);

        System.out.println(customer.getName());
        System.out.println(customer.getCustomerId());
        System.out.println(customer.getCheckingAccount().getBalance());
        System.out.println(customer.getSavingsAccount().getBalance());

        customer.getCheckingAccount().withdraw(10);
        customer.getCheckingAccount().withdraw(50);
        customer.getCheckingAccount().withdraw(40);
        customer.getCheckingAccount().withdraw(20);
        customer.getCheckingAccount().deposit(100);
        customer.getCheckingAccount().deposit(1000);
        System.out.println(customer.getCheckingAccount().getBalance());


        customer.getSavingsAccount().deposit(20);
        System.out.println(customer.getSavingsAccount().getBalance());

        System.out.println(customer.getSavingsAccount().getBalance() + customer.getCheckingAccount().getBalance());

        for (Transaction transaction : customer.getCheckingAccount().getTransactionsList()){
            System.out.println(transaction.getAmount());
            System.out.println(transaction.getType());
            System.out.println(transaction.getDate());
        }

    }
}
