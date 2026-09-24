package com.bank.services;

import com.bank.models.*;

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

    public CheckingAccount addCheckingAccount(Customer customer,String accountNumber, double balance){
        CheckingAccount account = new CheckingAccount(customer.getUserId(),accountNumber,balance);
        customer.setCheckingAccount(account);
        return account;
    }

    public SavingsAccount addSavingAccount(Customer customer,String accountNumber, double balance){
        SavingsAccount account = new SavingsAccount(customer.getUserId(),accountNumber,balance);
        customer.setSavingsAccount(account);
        return account;
    }

    public void addDebitCard(Account account, String cardNumber, String cardType){
        DebitCard card = new DebitCard(cardNumber,cardType);
        account.setDebitCard(card);
        fileService.saveAccount(account);
    }

    public void attachAccounts(List<Customer> customers, List<Account> accounts){
        for(Customer customer : customers){
            for(Account account : accounts){
                if(customer.getUserId().equals(account.getCustomerId())){
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
