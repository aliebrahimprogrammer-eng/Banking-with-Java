package com.bank.services;

import com.bank.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String customersFile = "data/customers.txt";
    private static final String accountsFile = "data/accounts.txt";
    private static final String transactionsFile = "data/transactions.txt";

    public void saveCustomer(String id, String name, String password){
        try{
            FileWriter writer = new FileWriter(customersFile, true);
            writer.write(
                    id + "," + name + "," + password + "\n"
            );
            writer.close();
        }catch (IOException e){
            System.out.println("Error saving customer");
        }
    }

    public void readCustomer(){
        try{
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(customersFile));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Error reading customer");
        }
    }

    public void saveAccount(Account account){
        try{
            FileWriter writer = new FileWriter(accountsFile, true);
            writer.write(
                    account.getCustomerId() + "," +
                            account.getAccountNumber() + "," +
                            getAccountType(account) + "," +
                            account.getBalance() + "," +
                            account.isActive() + "," +
                            account.getOverdraftCount() +
                            account.getDebitCard().getCardNumber() +
                            account.getDebitCard().getCardType() + "\n"
            );
            writer.close();
        } catch (IOException e){
            System.out.println("Error saving account.");
        }
    }

    public List<Customer> loadCustomers(){
        List<Customer> customers = new ArrayList<>();
        try{
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(customersFile));
            String line;
            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String id = parts[0];
                String name = parts[1];
                String password = parts [2];
                Customer customer = new Customer(id,name,password);
                customers.add(customer);

            }
            reader.close();
        }catch(IOException e){
            System.out.println("Error Loading Customers");
        }
        return customers;
    }

    public List<Account> loadAccounts(){
        List<Account> accountsList = new ArrayList<>();
        try{
            java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader(accountsFile));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String customerId = parts[0];
                String accountNumber = parts[1];
                String accountType = parts[2];
                double balance = Double.parseDouble(parts[3]);
                boolean active = Boolean.parseBoolean(parts[4]);
                int overdraftCount = Integer.parseInt(parts[5]);
                String cardNumber = parts[6];
                String cardType = parts[7];

                Account account;
                if(accountType.equals("Checking")){
                     account = new CheckingAccount(customerId,accountNumber,balance,active,overdraftCount);
                }else{
                     account = new SavingsAccount(customerId,accountNumber,balance,active,overdraftCount);
                }
                DebitCard card = new DebitCard(cardNumber,cardType);
                account.setDebitCard(card);
                accountsList.add(account);
            }
            reader.close();
        }catch(IOException e){
            System.out.println("Error loading accounts");
        }
        return accountsList;
    }

    public void updateAccount (Account updatedAccount){
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = (new BufferedReader(new FileReader(accountsFile)));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String accountNumber = parts[1];
                if (accountNumber.equals(updatedAccount.getAccountNumber())){
                    line = updatedAccount.getCustomerId()+","+
                            updatedAccount.getAccountNumber()+","+
                            getAccountType(updatedAccount)+","+
                            updatedAccount.getBalance()+","+
                            updatedAccount.isActive()+","+
                            updatedAccount.getOverdraftCount()+"," +
                            updatedAccount.getDebitCard().getCardNumber() + "," +
                            updatedAccount.getDebitCard().getCardType();

                }
                lines.add(line);
            }
                reader.close();
                FileWriter writer = new FileWriter(accountsFile);
                for(String updatedLine : lines){
                    writer.write(updatedLine+"\n");
                }
                writer.close();
        }catch (IOException e){
            System.out.println("Error updating account.");
            System.out.println(e);
        }
    }

    private String getAccountType(Account updatedAccount) {
        if(updatedAccount instanceof CheckingAccount){
            return "Checking";
        }else {
            return "Savings";
        }
    }

    public void saveTransaction (Account account, Transaction transaction){
        try{
            FileWriter writer = new FileWriter(transactionsFile, true);
            writer.write(
                    account.getAccountNumber() + ","
                    + transaction.getDate() + ","
                    + transaction.getType() + ","
                    + transaction.getAmount() + ","
                    + transaction.getBalanceAfter() + "\n"
            );
            writer.close();
        } catch (IOException e){
            System.out.println("Error saving transaction.");
        }
    }

    public void loadTransactions(List<Account> accounts){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(transactionsFile));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                String accountNumber = parts[0];
                String date = parts[1];
                String type = parts[2];
                double amount = Double.parseDouble(parts[3]);
                double balanceAfter = Double.parseDouble(parts[4]);

                for (Account account : accounts){
                    if (account.getAccountNumber().equals(accountNumber)){
                        Transaction transaction = new Transaction(amount,type,balanceAfter, LocalDateTime.parse(date));
                        account.addTransaction(transaction);
                    }
                }
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Error loading transactions");
            System.out.println(e);
        }
    }

    public void updateCustomerPassword(Customer customer){
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = (new BufferedReader(new FileReader(customersFile)));
            String line;
            while ((line = reader.readLine()) != null){
                String[] parts = line.split(",");
                String customerId = parts[0];
                if (customer.getCustomerId().equals(customerId)){
                    line = customer.getCustomerId() +","+
                            customer.getName() +","+
                            customer.getPassword();
                }
                lines.add(line);
            }
            reader.close();
            FileWriter writer = new FileWriter(customersFile);
            for(String updatedLine : lines){
                writer.write(updatedLine+"\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println("Error updating password.");
            System.out.println(e);
        }
    }



}
