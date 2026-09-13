package com.bank;

import com.bank.models.Account;
import com.bank.models.Banker;
import com.bank.models.Customer;
import com.bank.models.User;
import com.bank.services.BankService;
import com.bank.services.CustomerService;
import com.bank.services.FileService;
import com.bank.services.LoginService;

import java.util.List;
import java.util.Scanner;

public class BankApplication {

    private Scanner scanner;
    private LoginService loginService;
    private FileService fileService;
    private BankService bankService;


    public BankApplication(){
        scanner = new Scanner(System.in);
        fileService = new FileService();
        List<Customer> customers = fileService.loadCustomers();
        List<Account> accounts = fileService.loadAccounts();
        CustomerService customerService = new CustomerService();
        customerService.attachAccounts(customers,accounts);
        loginService = new LoginService(customers);
        bankService = new BankService(accounts);

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
        //String id = scanner.next();
        System.out.println("Password: ");
        //String password = scanner.next();
        String testId = "g2345";
        String testPass = "whodiz123";

        User user = loginService.login(testId,testPass);

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
                viewAccount(customer);
            } else if (choice==2) {
                deposit(customer);
            } else if (choice==3) {
               withdraw(customer);
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

    private void bankerMenu(Banker banker){}

    private void viewAccount(Customer customer){

        System.out.println("=========================");
        System.out.println("       My Accounts       ");
        System.out.println("=========================");

        if(customer.getCheckingAccount() != null){
            String activity = customer.getCheckingAccount().isActive() ? "Active" : "Inactive";
            System.out.println("Checking Account (" + activity +")");
            System.out.println("Account Number: " + customer.getCheckingAccount().getAccountNumber());
            System.out.println("Balance: " + customer.getCheckingAccount().getBalance());
        }
        if(customer.getSavingsAccount() != null){
            String activity = customer.getSavingsAccount().isActive() ? "Active" : "Inactive";
            System.out.println("Saving Account (" + activity +")");
            System.out.println("Account Number: " + customer.getSavingsAccount().getAccountNumber());
            System.out.println("Balance: " + customer.getSavingsAccount().getBalance());
        }

    }

    private void deposit(Customer customer) {
        System.out.println("=========================");
        System.out.println("         Deposit         ");
        System.out.println("=========================");

        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");

        System.out.println("Choose an account: ");

        int depositChoice = scanner.nextInt();
        String accountNumber;
        if (depositChoice ==1){
            if(customer.getCheckingAccount()==null){
                System.out.println("You do not have a checking account.");
                return;
            }
            accountNumber = customer.getCheckingAccount().getAccountNumber();
        }else if (depositChoice == 2){
            if(customer.getSavingsAccount()==null){
                System.out.println("You do not have a savings account.");
                return;
            }
            accountNumber = customer.getSavingsAccount().getAccountNumber();
        }else{
            System.out.println("Invalid account choice.");
            return;
        }
        System.out.println("Enter the amount you wish to deposit:");
        double amount = scanner.nextDouble();
        bankService.deposit(accountNumber,amount);
        System.out.println("Deposited " + amount +"$ to the account " + accountNumber + " was successful.");
    }

    private void withdraw(Customer customer) {
        System.out.println("=========================");
        System.out.println("         Withdraw         ");
        System.out.println("=========================");

        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");

        System.out.println("Choose an account: ");

        int depositChoice = scanner.nextInt();
        String accountNumber;
        if (depositChoice ==1){
            if(customer.getCheckingAccount()==null){
                System.out.println("You do not have a checking account.");
                return;
            }
            accountNumber = customer.getCheckingAccount().getAccountNumber();
        }else if (depositChoice == 2){
            if(customer.getSavingsAccount()==null){
                System.out.println("You do not have a savings account.");
                return;
            }
            accountNumber = customer.getSavingsAccount().getAccountNumber();
        }else{
            System.out.println("Invalid account choice.");
            return;
        }
        System.out.println("Enter the amount you wish to withdraw:");
        double amount = scanner.nextDouble();
        bankService.withdraw(accountNumber,amount);
    }

}
