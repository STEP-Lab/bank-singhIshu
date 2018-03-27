package com.step.Bank;

public class Account {
    private float balance;

    public Account(AccountNumber accountNumber, int balance) throws InsufficientBalance {
        validateMinimumBalance(balance, "Balance not enough to create account");
        this.balance = balance;
    }

    private void validateMinimumBalance(float balance, String s) throws InsufficientBalance {
        if (balance < 100) {
            throw new InsufficientBalance(s);
        }
    }


    public float getBalance() {
        return balance;
    }

    public void debit(int amount) throws InsufficientBalance {
        validateMinimumBalance(this.balance - amount, "Requested withdrawal will cause Insufficient Balance");
        this.balance -= amount;
    }


    public void credit(int amount) {
        this.balance += amount;
    }
}
