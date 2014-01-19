package com.moneykeeper.data;

import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 27.11.13
 * Time: 15:38
 * To change this template use File | Settings | File Templates.
 */
public class MoneyTest {

    @Test
    public void createMoneyTestLong() {

        double amount = 125.45;
        Money money = new Money(amount, Currency.getInstance(Locale.US));

        Currency currency = money.getCurrency();

        assertEquals(amount, money.getAmount(), 0.001);

    }
}
