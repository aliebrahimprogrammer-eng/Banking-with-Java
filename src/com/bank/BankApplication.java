package com.bank;

import com.bank.models.Customer;
import com.bank.models.User;
import com.bank.services.LoginService;

import java.util.Scanner;

public class BankApplication {

    private Scanner scanner;
    private LoginService loginService;

    public BankApplication(){
        scanner = new Scanner(System.in);
        loginService = new LoginService();
    }

    public void start(){
        boolean running = true;
        while(running){
            System.out.println("=========================");
            System.out.println("        ACME BANK        ");
            System.out.println("=========================");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.println("Choose an option:");
            int choice = scanner.nextInt();
            if (choice == 1) {
                login();
            } else if (choice == 2) {
                System.out.println("Goodbye!");
                running=false;
            }
        }

    }

    private void login(){
        System.out.println("Customer ID:" );
        String id = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();

        User user = loginService.login(id,password);

        if(user != null){
            System.out.println("Welcome " + user.getName());
            if (user instanceof Customer){
                customerMenu((Customer)user);
            }
        }else{
            System.out.println("Login failed");
        }
    }

    private void customerMenu(Customer customer){
        boolean loggedIn = true;
        while(loggedIn){
            System.out.println("=========================");
            System.out.println("      Customer Menu      ");
            System.out.println("=========================");
            System.out.println("1. View Accounts");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Statement");
            System.out.println("7. Logout");
            System.out.println("Choose an option:");
            int choice = scanner.nextInt();

            if(choice==1){
                System.out.println("1");
            } else if (choice==2) {
                System.out.println("2");
            } else if (choice==3) {
                System.out.println("3");
            } else if (choice==4) {
                System.out.println("4");
            } else if (choice==5) {
                System.out.println("5");
            } else if (choice==6) {
                System.out.println("6");
            } else if (choice==7) {
                System.out.println("Logged out.");
                loggedIn = false;
            } else {
                System.out.println("Invalid option.");
            }

        }
    }
}
