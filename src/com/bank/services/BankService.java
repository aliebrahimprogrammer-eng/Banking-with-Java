package com.bank.services;

import com.bank.interfaces.ITransactionOperations;
import com.bank.models.Account;
import java.util.ArrayList;
import java.util.List;

import com.bank.exceptions.AccountInactiveException;
import com.bank.exceptions.InsufficientFundException;
import com.bank.models.Customer;

public class BankService implements ITransactionOperations {

    private List<Account> accounts;
    private FileService fileService;

    public BankService(List<Account> accounts){
        this.accounts = accounts;
        this.fileService = new FileService();
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    private Account findAccount(String accountNumber) {
        for (Account account : accounts){
            if (account.getAccountNumber().equals(accountNumber)){
                return account;
            }
        }
        return null;
    }

    @Override
    public void deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null){
            account.deposit(amount);
            fileService.updateAccount(account);
            System.out.println("Deposited " + amount +"$ to the account " + accountNumber + " was successful" +
                    "and your balance now is " + account.getBalance() + "$.");
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null){
            try{
                account.withdraw(amount);
                fileService.updateAccount(account);
                System.out.println("Withdrew " + amount +"$ from the account " + accountNumber + " was successful" +
                        " and your current balance now is " + account.getBalance() + "$.");
            } catch (InsufficientFundException | AccountInactiveException e){
                System.out.println(e.getMessage());
            }

        }
    }

    @Override
    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) {
        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        if (fromAccount != null && toAccount != null){
            try {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                fileService.updateAccount(fromAccount);
                fileService.updateAccount(toAccount);
                System.out.println(amount + "$ is successfully transferred from your account " +
                        fromAccount.getAccountNumber() + " to " +toAccount.getAccountNumber() +
                        " and your balance is now " + fromAccount.getBalance() + "$.");
            } catch(InsufficientFundException | AccountInactiveException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
