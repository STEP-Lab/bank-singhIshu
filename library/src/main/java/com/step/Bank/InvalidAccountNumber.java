package com.step.Bank;

public class InvalidAccountNumber extends Throwable {
    public InvalidAccountNumber(String message) {
        super(message);
    }
}
