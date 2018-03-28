package com.step.Bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;


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

    public void print(PrintWriter writer){
        for (Transaction transaction:list){
            writer.println(transaction.toString());
        }
    }

    public Transactions filterAllCreditTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list){
            if (transaction instanceof CreditTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public Transactions filterAllDebitTransactions() {
        Transactions transactions = new Transactions();
        for (Transaction transaction : list){
            if (transaction instanceof DebitTransaction){
                transactions.list.add(transaction);
            }
        }
        return transactions;
    }

    public void writeTransactionsInCSV(CSVPrinter CSVPrinter) throws IOException {
        for (Transaction transaction : list){
            CSVPrinter.writeTransactions(transaction);
        }
    }
}
