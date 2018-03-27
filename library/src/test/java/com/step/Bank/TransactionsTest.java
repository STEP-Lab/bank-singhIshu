package com.step.Bank;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() throws Exception {
        transactions = new Transactions();
    }

    @Test
    public void mustRecordDebitTransaction() {
        transactions.debit(1000,"Arvind");
        assertThat(transactions.list,hasItem(new DebitTransaction(new Date(),1000,"Arvind")));
    }

    @Test
    public void mustRecordCreditTransaction() {
        transactions.credit(1000,"Debu");
        assertThat(transactions.list,hasItem(new CreditTransaction(new Date(),1000,"Debu")));
    }

    @Test
    public void mustRecordCreditAndDebitTransactions() {
        transactions.debit(1000, "Arvind");
        transactions.credit(1000, "Debu");
        CreditTransaction credit = new CreditTransaction(new Date(), 1000, "Debu");
        DebitTransaction debit = new DebitTransaction(new Date(),1000,"Arvind");
        assertThat(transactions.list, hasItems(credit,debit));
    }

    @Test
    public void filterTransactionsByAmount() {
        transactions.credit(1000,"Neeraj");
        transactions.credit(1500,"Pragya");
        transactions.debit(150,"Pragya");
        CreditTransaction credit = new CreditTransaction(1000, "Debu");
        DebitTransaction debit = new DebitTransaction(1000,"Arvind");
        Transactions transaction = transactions.filterByAmountGreaterThan(1000);
        System.out.println(transaction);
    }
}
