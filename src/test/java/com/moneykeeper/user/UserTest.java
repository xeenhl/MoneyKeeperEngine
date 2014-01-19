package com.moneykeeper.user;

import com.moneykeeper.TestUserHendler;
import com.moneykeeper.data.Input;
import com.moneykeeper.data.Money;
import com.moneykeeper.data.Type;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.util.Currency;
import java.util.Locale;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 04.12.13
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class UserTest {

    @Test
    public void WriteUserToXMLTest() {

        User inputUser = TestUserHendler.getUser();

        XStream steram = new XStream();
        steram.processAnnotations(User.class);

        System.out.print(steram.toXML(inputUser));



    }
}
