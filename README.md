# ACME Bank - Banking with Java

A command-line banking application built in Java

## Features

### Authentication

-   Customer and Banker login
-   Password hashing using SHA-256
-   Three failed login attempts temporarily lock the user for one minute
-   Customers can change their password
-   Bankers can create new customers and other bankers

### Customer Banking

-   Create Checking, Savings, or both account types
-   View account information and balance
-   Deposit money
-   Withdraw money
-   Transfer money between accounts
-   Transaction history
-   Transaction filtering:
    -   All transactions
    -   Today
    -   Yesterday
    -   Last 7 days
    -   Last 30 days
    -   Last week
    -   Last month
    -   Custom date/time range
-   Detailed account statement

### Accounts and Cards

-   Checking and Savings accounts
-   Account active/inactive status
-   Overdraft handling
-   Overdraft fee of \$35
-   Debit card types:
    -   Mastercard
    -   Mastercard Titanium
    -   Mastercard Platinum
-   Daily debit card limits and usage tracking

### Persistence

The application uses text files for data persistence:

``` text
data/
├── customers.txt
├── bankers.txt
├── accounts.txt
└── transactions.txt
```

Customers, bankers, accounts, and transactions are loaded when the
application starts and updated when changes are made.

## Project Structure

``` text
src/
└── com/
    └── bank/
        ├── Main.java
        ├── BankApplication.java
        │
        ├── models/
        │   ├── User.java
        │   ├── Customer.java
        │   ├── Banker.java
        │   ├── Account.java
        │   ├── CheckingAccount.java
        │   ├── SavingsAccount.java
        │   ├── DebitCard.java
        │   └── Transaction.java
        │
        ├── services/
        │   ├── BankService.java
        │   ├── CustomerService.java
        │   ├── FileService.java
        │   ├── LoginService.java
        │   └── PasswordService.java
        │
        ├── interfaces/
        │   └── ITransactionOperations.java
        │
        └── exceptions/
            ├── InsufficientFundException.java
            └── AccountInactiveException.java
```

## Exception Handling

Custom exceptions are used for banking rules:

-   `InsufficientFundException`
-   `AccountInactiveException`

These exceptions help prevent invalid banking operations from
terminating the application.

## Lambda Expressions

Lambda expressions are used with `forEach()` when displaying transaction
information.

Example:

``` java
account.getTransactionsList().forEach(transaction -> {
    printTransaction(transaction);
});
```

## Optional

`Optional<Account>` is used when searching for an account to avoid
returning a raw `null` from the account lookup method.

Example:

``` java
Account account = findAccount(accountNumber).orElse(null);
```

## Running the Application

1.  Open the project in IntelliJ IDEA.
2.  Make sure the Java SDK is configured.
3.  Make sure the required JUnit 4 dependency is available for the
    tests.
4.  Run `Main.java`.
5.  Use the command-line menus to log in and use the banking features.

- Note this is test accounts login info:
  
- Admin Account:
 
- id:admin
- password: admin 
  
- Customers Accounts:
  
- id:customer1
- password: customer1 
 
- id:customer2
- password: customer2


## Unit Testing

JUnit 4 is used for unit testing.

Current tests include `PasswordServiceTest`, which verifies that:

-   A password is hashed.
-   The hashed password is not the original plain-text password.
-   The same password produces the same hash.
- 
## Running Tests

From IntelliJ IDEA:

-   Open the PasswordServiceTest class.
-   Click the green Run icon next to the test class or individual test.

## Data Model / ERD

The final data model is represented by these main entities:

-   `User`
-   `Customer`
-   `Banker`
-   `Account`
-   `CheckingAccount`
-   `SavingsAccount`
-   `DebitCard`
-   `Transaction`

See image/ERD.png for the final diagram.

## Technologies

-   Java
-   Object-Oriented Programming
-   Java Collections
-   File I/O
-   Exception Handling
-   Lambda Expressions
-   `Optional`
-   JUnit 4
-   SHA-256 password hashing
