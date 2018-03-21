package com.step.Bank;

import java.util.Date;

public class DebitTransaction extends Transaction {

    protected DebitTransaction(Date date, float amount, String to) {
        super(date, to, amount);
    }

    public DebitTransaction(float amount, String name) {
        this(new Date(),amount, name);
    }
}


