package com.step.Bank;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TransactionTest {
    @Test
    public void mustRecordCorrectTransactionDate() {
        Date date = new Date();
        Transaction transaction = new DebitTransaction(date, 1000, "Another Account");
        assertThat(transaction.getDate(),is(date));
    }
}
