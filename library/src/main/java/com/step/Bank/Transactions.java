package com.step.Bank;

import java.io.Writer;
import java.util.ArrayList;


public class Transactions {
    protected ArrayList<Transaction> list;

    public Transactions() {
        this.list = new ArrayList<>();
    }

    public void debit(float amount, String name) {
        this.list.add(new DebitTransaction(amount,name));
    }

    public void credit(float amount, String name) { this.list.add(new CreditTransaction(amount,name)); }

    public Transactions filterByAmountGreaterThan(float amount) {
        Transactions transactions = new Transactions();
        for (Transaction transaction: list) {
            if (transaction.getAmount()>= amount){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

//    public void print(Writer writer){
//        for (Transaction transaction:list){
//            writer.println(transaction.toString());
//        }
//    }
}
