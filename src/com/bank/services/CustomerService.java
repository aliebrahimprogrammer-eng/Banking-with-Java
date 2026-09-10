package com.bank.services;

import com.bank.models.Account;
import com.bank.models.CheckingAccount;
import com.bank.models.Customer;
import com.bank.models.SavingsAccount;

import java.util.List;

public class CustomerService {

    private FileService fileService;
    private PasswordService passwordService;

    public CustomerService() {
        fileService = new FileService();
        passwordService = new PasswordService();
    }


    public Customer createCustomer(String id, String name, String password){
        String hashedPassword = passwordService.hashPassword(password);
        Customer customer = new Customer(id,name,hashedPassword);
        fileService.saveCustomer(id,name,hashedPassword);
        return customer;
    }

    public void addCheckingAccount(Customer customer,String accountNumber, double balance){
        CheckingAccount account = new CheckingAccount(customer.getCustomerId(),accountNumber,balance);
        customer.setCheckingAccount(account);
        fileService.saveAccount(customer.getCustomerId(),accountNumber,"Checking",balance);
    }

    public void addSavingAccount(Customer customer,String accountNumber, double balance){
        SavingsAccount account = new SavingsAccount(customer.getCustomerId(),accountNumber,balance);
        customer.setSavingsAccount(account);
        fileService.saveAccount(customer.getCustomerId(),accountNumber,"Savings",balance);
    }

    public void attachAccounts(List<Customer> customers, List<Account> accounts){
        for(Customer customer : customers){
            for(Account account : accounts){
                if(customer.getCustomerId().equals(account.getCustomerId())){
                    if(account instanceof CheckingAccount){
                        customer.setCheckingAccount((CheckingAccount)account);
                    }
                    else if (account instanceof SavingsAccount){
                        customer.setSavingsAccount((SavingsAccount)account);
                    }
                }
            }
        }
    }

}
