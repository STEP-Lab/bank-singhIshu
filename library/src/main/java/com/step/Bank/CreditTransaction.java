package com.step.Bank;

import java.util.Date;

public class CreditTransaction extends Transaction {
    protected CreditTransaction(Date date,float amount,String to) {
        super(date, to, amount);
    }

    public CreditTransaction(float amount,String to) {
        this(new Date(),amount,to);
    }
}

