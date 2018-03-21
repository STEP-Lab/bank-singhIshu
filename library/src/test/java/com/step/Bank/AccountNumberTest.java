package com.step.Bank;

import org.junit.Test;

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
