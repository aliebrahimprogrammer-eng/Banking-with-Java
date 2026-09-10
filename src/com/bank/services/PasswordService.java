package com.bank.services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordService {
    public String hashPassword(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(password.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : hashedPassword){
                result.append(String.format("%02x",b));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e){
            System.out.println(e);
            throw new RuntimeException("Hashing algorithm not available.");

        }
    }
}
