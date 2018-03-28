package com.step.Bank;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.junit.Assert.assertThat;

public class TransactionsTest {

    private Transactions transactions;

    @Before
    public void setUp() {
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
        transactions.credit(1001,"Neeraj");
        transactions.credit(11,"Pragya");
        transactions.debit(1001,"Pragya");
        CreditTransaction credit = new CreditTransaction(1001, "Neeraj");
        DebitTransaction debit = new DebitTransaction(1001,"Pragya");
        Transactions filteredTransactions = transactions.filterByAmountGreaterThan(1000);
        assertThat(filteredTransactions.list,hasItems(credit,debit));
    }


    @Test
    public void printsTheTransactionInToString() throws FileNotFoundException, UnsupportedEncodingException {
        transactions.debit(1000, "Arvind");
        transactions.credit(1000, "Debu");
        CreditTransaction credit = new CreditTransaction(new Date(), 1000, "Debu");
        DebitTransaction debit = new DebitTransaction(new Date(),1000,"Arvind");
        ArrayList<String> result = new ArrayList<>();
        PrintWriter writer = new PrintWriter("file.txt","UTF-8"){
            @Override
            public void println(String x) {
                result.add(x);
            }
        };
        this.transactions.print(writer);
        writer.close();
        assertThat(result,hasItems(credit.toString(),debit.toString()));
    }

    @Test
    public void filtersAllCreditTransactions() {
        transactions.credit(1000,"Neeraj");
        transactions.credit(15000,"Pragya");
        transactions.debit(150,"Pragya");
        CreditTransaction credit1 = new CreditTransaction(1000, "Neeraj");
        CreditTransaction credit2 = new CreditTransaction(15000, "Pragya");
        Transactions transaction = transactions.filterAllCreditTransactions();
        assertThat(transaction.list,hasItems(credit1,credit2));
    }

    @Test
    public void filtersAllDebitTransactions() {
        transactions.debit(1000,"Pragya");
        transactions.credit(15000,"Pragya");
        transactions.debit(15000,"Anjali");
        DebitTransaction debit1 = new DebitTransaction(1000, "Pragya");
        DebitTransaction debit2 = new DebitTransaction(15000, "Anjali");
        Transactions transaction = transactions.filterAllDebitTransactions();
        assertThat(transaction.list,hasItems(debit1,debit2));
    }

    @Test
    public void printAllTheTransactionsInCSVFile() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        transactions.credit(15000,"Ashish");
        String headers = "Date,Amount,To";
        Transaction creditToKetan = new CreditTransaction(15000,"Ashish");
        FileWriter fileWriter = new FileWriter("file.csv") {
            @Override
            public Writer append(CharSequence csq) {
                result.add(String.valueOf(csq));
                return this;
            }
        };
        CSVPrinter csvPrinter = new CSVPrinter(fileWriter,headers);
        csvPrinter.writeHeaders();
        transactions.writeTransactionsInCSV(csvPrinter);
        csvPrinter.close();
        assertThat(result,hasItems(String.valueOf(creditToKetan.getDate()),String.valueOf(creditToKetan.getAmount()), creditToKetan.getReciever()));
    }
}
