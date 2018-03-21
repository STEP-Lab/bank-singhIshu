package com.step.Bank;

public class InsufficientBalance extends Throwable {


    public InsufficientBalance(String message) {
        super(message);
    }
}
