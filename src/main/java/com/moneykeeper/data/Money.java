package com.moneykeeper.data;

import com.moneykeeper.ecxeption.WrongCurrencyException;

import java.util.Currency;
import java.util.Locale;


/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 22.11.13
 * Time: 17:40
 * To change this template use File | Settings | File Templates.
 */
public class Money {

    private long amount;
    private Currency currency;

    public Money(long amount, Currency currency) {

        this.currency = currency;
        this.amount = amount * centFactor();

    }

    public Money(double amount, Currency currency) {

        this.currency = currency;
        this.amount = Math.round(amount * centFactor());

    }

    public double getAmount() {
        return (double)amount/centFactor();
    }

    public Currency getCurrency() {
        return currency;
    }

    private Money() {}

    public static Money dollars(double amount) {

        return new Money(amount, Currency.getInstance(Locale.US));

    }

    public static Money local(double amount) {

        return new Money(amount, Currency.getInstance(Locale.getDefault()));

    }

    public boolean equals(Object o) {

        if(this == o) return true;

        if(o == null || getClass() != o.getClass()) return false;

        Money money = (Money)o;

        if(amount != money.getAmount()) return false;
        if(currency != null? !currency.equals(money.getCurrency()): money.currency != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (int) (amount ^ (amount>>>32));
        result = 31 * result + (currency != null? currency.hashCode() : 0);
        return result;
    }

    public Money add(Money other) throws WrongCurrencyException{
        assertSameCurrency(other.getCurrency());
        return newMoney(this.amount + other.amount);
    }

    public Money subtract(Money other) throws WrongCurrencyException {
        assertSameCurrency(other.getCurrency());
        return newMoney(this.amount - other.amount);
    }

    public Money multiply(double arg) {
        return new Money(getAmount() * arg, currency);
    }

    public int compareTo(Object o) throws WrongCurrencyException{
        return compareTo((Money)o);
    }

    public int compareTo(Money money) throws WrongCurrencyException{
        assertSameCurrency(money.getCurrency());
        if(amount < money.amount) return -1;
        if(amount == money.amount) return 0;
        return 1;
    }

    public boolean greaterThan(Money money) throws WrongCurrencyException{
        return (compareTo(money) > 0);
    }

    public Money[] allocate(int n) {

        Money lowResult = newMoney(amount/n);
        Money highResult = newMoney(lowResult.amount + 1);
        Money result[] = new Money[n];
        int reminder = (int)amount % n;

        for(int i = 0; i < reminder; i++)
            result[i] = highResult;
        for(int i = reminder; i <n; i++)
            result[i] = lowResult;

        return result;

    }

    public Money[] allocate(long[] ration) {

        long total = 0;

        for(long e : ration)
            total += e;

        long reminder = amount;
        Money[] result = new Money[ration.length];

        for(int i=0; i<result.length; i++) {
            result[i] = newMoney(reminder * ration[i]/total);
            reminder -= result[i].amount;
        }

        return result;

    }

    public String toString() {

        return Double.toString(getAmount());
    }


    private void assertSameCurrency(Currency currency) throws WrongCurrencyException{
        if(!this.currency.equals(currency)) throw new WrongCurrencyException();
    }

    private Money newMoney(long amount) {
        Money money = new Money();
        money.currency = this.currency;
        money.amount = amount;
        return money;
    }

    private int[] centFactors = {1, 10, 100, 1000};
    private int centFactor() {

        return centFactors[currency.getDefaultFractionDigits()];

    }
}
