package com.bank;

import com.bank.models.*;
import com.bank.services.BankService;
import com.bank.services.CustomerService;
import com.bank.services.FileService;
import com.bank.services.LoginService;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankApplication application = new BankApplication();
        application.start();
    }
}
