package com.bank.services;

import com.bank.models.Customer;
import com.bank.models.User;

import java.util.List;

public class LoginService {

    private PasswordService passwordService;

    public LoginService() {
        passwordService = new PasswordService();
    }


    public User login (String customerId, String password){
        FileService fileService = new FileService();
        List<Customer> userList = fileService.loadCustomers();
        String hashedPassword = passwordService.hashPassword(password);
        for (User user : userList){
            if(user.getCustomerId().equals(customerId) && user.getPassword().equals(hashedPassword)){
             return user;
            }
        }
        return null;
    }
}
