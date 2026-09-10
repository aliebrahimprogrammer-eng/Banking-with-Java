package com.bank.services;

import com.bank.models.Account;
import com.bank.models.CheckingAccount;
import com.bank.models.Customer;
import com.bank.models.SavingsAccount;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    private static final String customersFile = "data/customers.txt";
    private static final String accountsFile = "data/accounts.txt";

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

    public void saveAccount(String customerId,String accountNumber,String accountType, double balance){
        try{
            FileWriter writer = new FileWriter(accountsFile, true);
            writer.write(
                    customerId + "," + accountNumber + "," + accountType + "," + balance + "\n"
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

                Account account;
                if(accountType.equals("Checking")){
                     account = new CheckingAccount(customerId,accountNumber,balance);
                }else{
                     account = new SavingsAccount(customerId,accountNumber,balance);
                }
                accountsList.add(account);
            }
            reader.close();
        }catch(IOException e){
            System.out.println("Error loading accounts");
        }
        return accountsList;
    }
}
