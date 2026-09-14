package com.bank;

import com.bank.models.*;
import com.bank.services.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
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
        fileService.loadTransactions(accounts);
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
        String id = scanner.next();
        System.out.println("Password: ");
        String password = scanner.next();
        //String testId = "g2345";
        //String testPass = "whodiz123";

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
            System.out.println("7. Set password");
            System.out.println("8. Logout");
            System.out.println("Choose an option:");
            int choice = scanner.nextInt();

            if(choice==1){
                viewAccount(customer);
            } else if (choice==2) {
                deposit(customer);
            } else if (choice==3) {
               withdraw(customer);
            } else if (choice==4) {
                transfer(customer);
            } else if (choice==5) {
                transactionHistory(customer);
            } else if (choice==6) {
                statement(customer);
            } else if (choice==7) {
                changePassword(customer);
            } else if (choice==8) {
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

    private void transfer(Customer customer) {
        System.out.println("=========================");
        System.out.println("         Transfer         ");
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
            System.out.println("Invalid option.");
            return;
        }
        System.out.println("Enter the account number you wish to transfer to:");
        String toAccount = scanner.next();
        System.out.println("Enter the amount you wish to transfer:");
        double amount = scanner.nextDouble();
        bankService.transfer(accountNumber,toAccount,amount);
    }

    private void transactionHistory(Customer customer) {
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Back");
        System.out.println("Choose an account: ");

        int choice = scanner.nextInt();
        if (choice ==1){
            if (customer.getCheckingAccount() == null){
                System.out.println("You do not have a checking accounts.");
                return;
            }
            showTransactionsMenu(customer.getCheckingAccount());
        }else if (choice ==2){
            if (customer.getSavingsAccount() == null){
                System.out.println("You do not have a savings accounts.");
                return;
            }
            showTransactionsMenu(customer.getSavingsAccount());
        }else if (choice ==3){
            return;
        }else{
            System.out.println("Invalid option.");
        }
    }

    public void showTransactions(Account account){
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());

        for(Transaction transaction : account.getTransactionsList()){
            printTransaction(transaction);
        }
    }

    public void showTodayTransactions(Account account){
        LocalDate today = LocalDate.now();
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Today");
        for(Transaction transaction : account.getTransactionsList()){
            if(transaction.getDate().toLocalDate().equals(today)){
                printTransaction(transaction);
            }
        }
    }

    public void showYesterdayTransactions(Account account){
        LocalDate today = LocalDate.now().minusDays(1);
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Yesterday");
        for(Transaction transaction : account.getTransactionsList()){
            if(transaction.getDate().toLocalDate().equals(today)){
                printTransaction(transaction);
            }
        }
    }

    public void showLast7DaysTransactions(Account account){
        LocalDate today = LocalDate.now();
        LocalDate sevenDaysAgo = today.minusDays(7);
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Last 7 Days");
        for(Transaction transaction : account.getTransactionsList()){
            LocalDate transactionDate = transaction.getDate().toLocalDate();
            if(!transactionDate.isBefore(sevenDaysAgo) && !transactionDate.isAfter(today)){
                printTransaction(transaction);
            }
        }
    }

    public void showLast30DaysTransactions(Account account){
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAgo = today.minusDays(30);
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Last 7 Days");
        for(Transaction transaction : account.getTransactionsList()){
            LocalDate transactionDate = transaction.getDate().toLocalDate();
            if(!transactionDate.isBefore(thirtyDaysAgo) && !transactionDate.isAfter(today)){
                printTransaction(transaction);
            }
        }
    }

    public void showLastWeekTransactions(Account account){
        LocalDate today = LocalDate.now();
        LocalDate startOfLastWeek = today.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY));
        LocalDate endOfLastWeek = startOfLastWeek.plusDays(6);

        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Last Week");
        for(Transaction transaction : account.getTransactionsList()){
            LocalDate transactionDate = transaction.getDate().toLocalDate();
            if(!transactionDate.isBefore(startOfLastWeek) && !transactionDate.isAfter(endOfLastWeek)){
                printTransaction(transaction);
            }
        }
    }

    public void showLastMonthTransactions(Account account){
        LocalDate today = LocalDate.now();
        LocalDate startOfLastMonth = today.withDayOfMonth(1).minusMonths(1);
        LocalDate endOfLastMonth = today.withDayOfMonth(1).minusDays(1);

        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Last 7 Days");
        for(Transaction transaction : account.getTransactionsList()){
            LocalDate transactionDate = transaction.getDate().toLocalDate();
            if(!transactionDate.isBefore(startOfLastMonth) && !transactionDate.isAfter(endOfLastMonth)){
                printTransaction(transaction);
            }
        }
    }

    public void showCustomRangeTransactions(Account account){
        System.out.println("=========================");
        System.out.println("   Transaction History   ");
        System.out.println("=========================");

        scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Enter start date and time in this pattern (yyyy-MM-dd HH:mm): ");
        String start = scanner.nextLine();
        System.out.println("Enter end date and time in this pattern (yyyy-MM-dd HH:mm): ");
        String end = scanner.nextLine();
        LocalDateTime startDateTime = LocalDateTime.parse(start,formatter);
        LocalDateTime endDateTime = LocalDateTime.parse(end,formatter);

        System.out.println("\nAccount: " + account.getAccountNumber());
        System.out.println("Transaction Filter : Custom from " + startDateTime + " to " + endDateTime);
        for(Transaction transaction : account.getTransactionsList()){
            LocalDateTime transactionDate = transaction.getDate();
            if(!transactionDate.isBefore(startDateTime) && !transactionDate.isAfter(endDateTime)){
                printTransaction(transaction);
            }
        }
    }

    public void printTransaction(Transaction transaction){
        System.out.println(transaction.getDate() + " | " +
                transaction.getType() + " | Amount: " +
                transaction.getAmount() + "$ | Balance: " +
                transaction.getBalanceAfter() + "$");
    }


    public void showTransactionsMenu(Account account){
        boolean running = true;
        while (running) {
            System.out.println("=========================");
            System.out.println("   Transaction History   ");
            System.out.println("=========================");

            System.out.println("\nAccount: " + account.getAccountNumber());
            System.out.println("1. All Transactions");
            System.out.println("2. Today");
            System.out.println("3. Yesterday");
            System.out.println("4. Last 7 Days");
            System.out.println("5. Last 30 Days");
            System.out.println("6. Last week");
            System.out.println("7. Last month");
            System.out.println("8. Custom Date/Time");
            System.out.println("9. Back");
            System.out.println("Choose an account: ");
            int choice = scanner.nextInt();

            if(choice==1){
                showTransactions(account);
            } else if (choice==2) {
                showTodayTransactions(account);
            } else if (choice==3) {
                showYesterdayTransactions(account);
            } else if (choice==4) {
                showLast7DaysTransactions(account);
            } else if (choice==5) {
                showLast30DaysTransactions(account);
            } else if (choice==6) {
                showLastWeekTransactions(account);
            } else if (choice==7) {
                showLastMonthTransactions(account);
            } else if (choice==8) {
                showCustomRangeTransactions(account);
            } else if (choice==9) {
                running = false;
            } else {
                System.out.println("Invalid option.");
            }

        }
    }

    private void statement(Customer customer) {
        System.out.println("=========================");
        System.out.println("    Account Statement    ");
        System.out.println("=========================");

        System.out.println("1. Checking Account");
        System.out.println("2. Savings Account");
        System.out.println("3. Back");
        System.out.println("Choose an account: ");

        int choice = scanner.nextInt();
        if (choice ==1){
            if (customer.getCheckingAccount() == null){
                System.out.println("You do not have a checking accounts.");
                return;
            }
            showStatement(customer.getCheckingAccount());
        }else if (choice ==2){
            if (customer.getSavingsAccount() == null){
                System.out.println("You do not have a savings accounts.");
                return;
            }
            showStatement(customer.getSavingsAccount());
        }else if (choice ==3){
            return;
        }else{
            System.out.println("Invalid option.");
        }
    }

    public void showStatement(Account account){
        System.out.println();
        System.out.println("=========================");
        System.out.println("    Account Statement    ");
        System.out.println("=========================");

        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Current Balance: " + account.getBalance() + "$");
        System.out.println("Account Status: " + (account.isActive() ? "Active" : "Inactive"));
        System.out.println("=========================");
        for (Transaction transaction : account.getTransactionsList()){
            printTransaction(transaction);
        }
        System.out.println("=========================");
        System.out.println("Current Balance: " + account.getBalance() + "$");
        System.out.println("=========================");

    }

    private void changePassword(Customer customer){
        scanner.nextLine();
        System.out.println("Please enter your new password: ");
        String newPassword = scanner.next();
        PasswordService passwordService = new PasswordService();
        customer.setPassword(passwordService.hashPassword(newPassword));
        fileService.updateCustomerPassword(customer);
        System.out.println("Your password was changed successfully.");
    }




}
