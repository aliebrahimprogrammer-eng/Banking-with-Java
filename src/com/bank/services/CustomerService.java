package com.bank.services;

import com.bank.models.Customer;

public class CustomerService {

    private FileService fileService;

    public CustomerService(){
        fileService = new FileService();
    }

    public Customer createCustomer(String id, String name, String password){
        Customer customer = new Customer(id,name,password);
        fileService.saveCustomer(id,name,password);
        return customer;
    }

}
