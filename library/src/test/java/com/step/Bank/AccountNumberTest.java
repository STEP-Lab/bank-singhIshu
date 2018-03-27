package com.step.Bank;

import org.junit.Test;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;
import static org.junit.Assert.assertThat;

public class AccountNumberTest {
    @Test(expected = InvalidAccountNumber.class)
    public void checkAccountNumberValidity() throws InvalidAccountNumber {
        new AccountNumber("1234");
    }

    @Test (expected = InvalidAccountNumber.class)
    public void checkAccountNumberDoesNotHaveAlphabets() throws InvalidAccountNumber {
        new AccountNumber("123a-23ab");
    }


}
