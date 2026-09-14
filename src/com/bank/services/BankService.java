package com.bank.services;

import com.bank.interfaces.ITransactionOperations;
import com.bank.models.Account;
import java.util.ArrayList;
import java.util.List;

import com.bank.exceptions.AccountInactiveException;
import com.bank.exceptions.InsufficientFundException;
import com.bank.models.Customer;
import com.bank.models.DebitCard;
import com.bank.models.Transaction;

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
            DebitCard card = account.getDebitCard();
            if (card == null){
                System.out.println("This account does not have a debit card.");
                return;
            }
            if (!card.canDepositToOwnAccount(amount)){
                System.out.println("Daily deposit limit exceeded.");
                return;
            }
            account.deposit(amount);
            card.addOwnAccountDepositDailyUsage(amount);

            fileService.updateAccount(account);
            Transaction newestTransaction = account.getTransactionsList().get(account.getTransactionsList().size() -1 );
            System.out.println(newestTransaction.getDate());
            System.out.println(newestTransaction.getType());
            fileService.saveTransaction(account,newestTransaction);
            System.out.println("Deposited " + amount +"$ to the account " + accountNumber + " was successful" +
                    "and your balance now is " + account.getBalance() + "$.");
        }
    }

    @Override
    public void withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);
        if (account != null){
            try{
                DebitCard card = account.getDebitCard();
                if (card == null){
                    System.out.println("This account does not have a debit card.");
                    return;
                }
                if (!card.canWithdraw(amount)){
                    System.out.println("Daily withdrawal limit exceeded.");
                    return;
                }
                int transactionCountBeforeOperation = account.getTransactionsList().size();
                account.withdraw(amount);
                card.addWithdrawDailyUsage(amount);
                for (int i = transactionCountBeforeOperation; i < account.getTransactionsList().size(); i++){
                    Transaction transaction = account.getTransactionsList().get(i);
                    fileService.saveTransaction(account,transaction);
                }
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
            DebitCard card = fromAccount.getDebitCard();
            if (card == null){
                System.out.println("This account does not have a debit card.");
                return;
            }
            boolean ownAccountTransfer = fromAccount.getCustomerId().equals(toAccount.getCustomerId());
            if(ownAccountTransfer){
                if (!card.canTransferToOwnAccount(amount)){
                    System.out.println("Daily own-account transfer limit exceeded.");
                    return;
                }else{
                    if (!card.canTransfer(amount)){
                        System.out.println("Daily transfer limit exceeded.");
                        return;
                    }
                }
            }

            try {
                int transactionCountBeforeOperationFrom = fromAccount.getTransactionsList().size();
                int transactionCountBeforeOperationTo = toAccount.getTransactionsList().size();

                fromAccount.withdraw(amount);
                toAccount.deposit(amount);

                if(ownAccountTransfer){
                    card.addOwnAccountTransferDailyUsage(amount);
                }else{
                    card.addTransferDailyUsage(amount);
                }

                for (int i = transactionCountBeforeOperationFrom; i < fromAccount.getTransactionsList().size(); i++){
                    Transaction transaction = fromAccount.getTransactionsList().get(i);
                    fileService.saveTransaction(fromAccount,transaction);
                }
                for (int i = transactionCountBeforeOperationTo; i < toAccount.getTransactionsList().size(); i++){
                    Transaction transaction = toAccount.getTransactionsList().get(i);
                    fileService.saveTransaction(toAccount,transaction);
                }

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
