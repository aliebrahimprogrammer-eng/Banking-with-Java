package com.bank;

import com.bank.models.CheckingAccount;
import com.bank.models.Customer;
import com.bank.models.SavingsAccount;

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
        System.out.println(customer.getCheckingAccount().getBalance());


        customer.getSavingsAccount().deposit(20);
        System.out.println(customer.getSavingsAccount().getBalance());

        System.out.println(customer.getSavingsAccount().getBalance() + customer.getCheckingAccount().getBalance());

        System.out.println();

    }
}
