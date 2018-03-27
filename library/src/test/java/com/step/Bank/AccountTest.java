package com.step.Bank;

import com.step.Bank.Account;
import com.step.Bank.AccountNumber;
import com.step.Bank.InsufficientBalance;
import com.step.Bank.InvalidAccountNumber;
import com.sun.org.apache.xerces.internal.util.PropertyState;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {

    private Account account;

    @Before
    public void setUp() throws InsufficientBalance, InvalidAccountNumber {
        account = new Account(new AccountNumber("1234-1223"), 1000);
    }

    @Test
    public void getBalance(){
        assertThat(account.getBalance(), is((1000.0F)));
    }


    @Test (expected=InsufficientBalance.class)
    public void checkMinimumBalance() throws InsufficientBalance, InvalidAccountNumber {
        Account ishu = new Account(new AccountNumber("1235-1234"),10);
    }

    @Test (expected=InsufficientBalance.class)
    public void checkWithdrawLimit() throws InsufficientBalance {
        account.debit(1000);
    }

    @Test
    public void checkIfWithdrawalIsAllowedIfBalanceIsGreaterThanMinimumBalance() throws InsufficientBalance, InvalidAccountNumber {
        Account account = new Account(new AccountNumber("1235-1234"), 20000);
        account.debit(1000);
        assertThat(account.getBalance(),is(19000.0F));
    }

    @Test
    public void checkIfAmountIsBeingAddedToAccountBalance() {
        account.credit(1200);
        assertThat(account.getBalance(),is(2200.0F));
    }

    @Test
    public void checkIfTwoInstancesOfSameClassHaveSameHashCode() throws InsufficientBalance, InvalidAccountNumber {
        Account account1 = new Account(new AccountNumber("1234-1234"),1000);
        Account account2 = new Account(new AccountNumber("1234-1234"),1000);
        System.out.println(account1.hashCode());
        System.out.println(account2.hashCode());
    }
}
