package com.bank.services;

import com.bank.models.Customer;
import com.bank.models.User;

import java.util.List;

public class LoginService {

    public User login (String customerId, String password){
        FileService fileService = new FileService();
        List<Customer> userList = fileService.loadCustomers();
        for (User user : userList){
            if(user.getCustomerId().equals(customerId) && user.getPassword().equals(password)){
             return user;
            }
        }
        return null;
    }
}
