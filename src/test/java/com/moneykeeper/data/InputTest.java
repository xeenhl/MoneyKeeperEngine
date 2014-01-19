package com.moneykeeper.data;

import org.junit.Before;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 27.11.13
 * Time: 13:47
 * To change this template use File | Settings | File Templates.
 */
public class InputTest {

    @Test
    public void createInputTestDouble() {

        Money money = new Money(12.55, Currency.getInstance(Locale.US));
        Input testInput = new Input(money, Type.INCOME, Category.INCOME_SALARY);

        assertEquals("amount is incorrectly saved", 12.55 , testInput.getAmount().getAmount(), 0.001);
        assertEquals("Wrong type saved", Type.INCOME, testInput.getType());
        assertEquals("Worng Category saved", Category.INCOME_SALARY, testInput.getCategory());

    }

    @Test
    public void createInputTestLong() {

        Money money = new Money(12l, Currency.getInstance(Locale.getDefault()));
        Input testInput = new Input(money, Type.INCOME, Category.INCOME_SALARY);

        assertEquals("amount is incorrectly saved", 12.00 , testInput.getAmount().getAmount(), 0.001);
        assertEquals("Wrong type saved", Type.INCOME, testInput.getType());
        assertEquals("Worng Category saved", Category.INCOME_SALARY, testInput.getCategory());

    }

    @Test
    public  void idGeberatorTest() {

        Money money = new Money(12l, Currency.getInstance(Locale.getDefault()));
        Input testInput = new Input(money, Type.INCOME, Category.INCOME_SALARY);

        Input testInput2 = new Input(money, Type.INCOME, Category.INCOME_SALARY);

        System.out.println(testInput.getId());
        System.out.println(testInput2.getId());

        assertFalse((testInput.getId().equals(testInput2.getId())));



    }

}
