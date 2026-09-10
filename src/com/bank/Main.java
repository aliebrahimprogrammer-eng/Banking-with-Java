package com.bank;

import com.bank.models.*;
import com.bank.services.BankService;
import com.bank.services.CustomerService;
import com.bank.services.FileService;
import com.bank.services.LoginService;

import java.util.List;

public class Main {
    public static void main(String[] args){

        BankService bankService = new BankService();

        Customer customer = new Customer("123456789D","Ali Ebrahim","password123");
        Banker banker = new Banker("897645321","Ahmed Hani","qwerty123");
        CheckingAccount cAccount = new CheckingAccount("123456789D","54321",300);
        SavingsAccount sAccount = new SavingsAccount("897645321","12345",200);

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
        System.out.println("-----------------------------------");

        FileService fileService = new FileService();
        //fileService.saveCustomer(customer.getCustomerId(), customer.getName(),customer.getPassword());
        //System.out.println("Customers saved.");
        //System.out.println("-----------------------------------");
        //fileService.readCustomer();

        CustomerService customerService = new CustomerService();
        /*Customer customer2 = customerService.createCustomer("h3214","Fatima Ali","comingsoon123");
        customerService.addCheckingAccount(customer2,"abcd1234",30);
        customerService.addSavingAccount(customer2,"abcd1234",60);
        fileService.readCustomer();
        System.out.println(customer2.getSavingsAccount().getBalance());
        System.out.println(customer2.getCheckingAccount().getBalance());*/
        List<Customer> customerList = fileService.loadCustomers();
        for (Customer cust : customerList){
            System.out.println(cust.getCustomerId() + " - " + cust.getName());
        }
        System.out.println("-----------------------------------");
        List<Account> accs = fileService.loadAccounts();
        for (Account acc : accs){
            System.out.println(acc.getAccountNumber()+ " - " + acc.getBalance());
        }
        System.out.println("-----------------------------------");
        customerService.attachAccounts(customerList,accs);
        for(Customer customerX : customerList){
            System.out.println("Customer " + customerX.getName());
            if(customerX.getCheckingAccount() != null){
                System.out.println(customerX.getCheckingAccount().getBalance());
            }
            if(customerX.getSavingsAccount() != null){
                System.out.println(customerX.getSavingsAccount().getBalance());
            }
        }
        System.out.println("-----------------------------------");

        LoginService loginService = new LoginService();
        User loggedIn1 = loginService.login("123456789D","password1234");
        User loggedIn2 = loginService.login("123456789D","password123");
        if(loggedIn1 != null){
            if(loggedIn2 instanceof Customer){
                System.out.println("Welcome Customer");
            }
            if(loggedIn2 instanceof Banker){
                System.out.println("Welcome Banker");
            }
        }else{
            System.out.println("Invalid Login");
        }
        if(loggedIn2 != null){
            if(loggedIn2 instanceof Customer){
                System.out.println("Welcome Customer");
            }
            if(loggedIn2 instanceof Banker){
                System.out.println("Welcome Banker");
            }
        }else{
            System.out.println("Invalid Login");
        }

    }
}
