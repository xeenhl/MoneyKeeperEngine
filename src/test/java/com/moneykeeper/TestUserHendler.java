package com.moneykeeper;

import com.moneykeeper.data.Input;
import com.moneykeeper.data.Money;
import com.moneykeeper.data.Type;
import com.moneykeeper.user.User;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 04.12.13
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public class TestUserHendler {

    public static User getUser() {

        User user = new User("Oleg", "qwerty12345");

        Set<Input> inputs = user.getInputs();

        for(int i =0; i <50; i++) {
            inputs.add(new Input(new Money((long)i, Currency.getInstance(Locale.US)), Type.INCOME));
        }

        user.updateInputs(inputs);

        return user;

    }
}
