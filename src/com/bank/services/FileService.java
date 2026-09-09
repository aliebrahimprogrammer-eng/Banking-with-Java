package com.bank.services;

import java.io.FileWriter;
import java.io.IOException;

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
}
