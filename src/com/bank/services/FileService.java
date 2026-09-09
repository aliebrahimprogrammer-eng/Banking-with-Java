package com.bank.services;

import java.io.FileWriter;
import java.io.IOException;

public class FileService {
    private static final String customersFile = "data/customers.txt";

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
}
