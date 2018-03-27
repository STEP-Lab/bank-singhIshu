package com.step.Bank;

public class AccountNumber {
    private String number;

    public AccountNumber(String number) throws InvalidAccountNumber {
        this.number = number;
        validateAccountNumber(number);
    }
    private void validateAccountNumber(String accountNumber) throws InvalidAccountNumber {
        if (!accountNumber.matches("\\d{4}-\\d{4}")) {
            throw new InvalidAccountNumber("Account number is invalid");
        }
    }

}
