package com.bank.models;
import com.bank.exceptions.AccountInactiveException;
import com.bank.exceptions.InsufficientFundException;

public class CheckingAccount extends Account{

    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void withdraw (double amount) throws InsufficientFundException, AccountInactiveException {
        if(!isActive()){
            throw new AccountInactiveException("Account is inactive.");
        }
        else if (amount <= 0){
            throw new InsufficientFundException("Amount must be greater than 0.");
        }
        else if(amount > getBalance()){
            processOverdraft(amount);
        }
        else {
            setBalance(getBalance() - amount);
            addTransaction(new Transaction(amount, "Withdraw"));
        }
    }
}
