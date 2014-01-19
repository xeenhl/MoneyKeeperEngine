package com.moneykeeper.logic;


import com.moneykeeper.TestUserHendler;
import com.moneykeeper.settings.Settings;
import com.moneykeeper.user.User;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;

import java.io.PrintWriter;

import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 04.12.13
 * Time: 17:08
 * To change this template use File | Settings | File Templates.
 */
public class DataLoaderTest {


    @Test
    public void SaveUserToFileTest() {

        User user = TestUserHendler.getUser();
        DataLoader.save(user);
    }

    @Test
    public void LoadUserFromFile() {

        User user = TestUserHendler.getUser();
        DataLoader.save(user);

        User check = new User(user.getUserName(), user.getUserPassword());

        User out = DataLoader.load(check);

        assertEquals(user.getUserName(), out.getUserName());
        assertEquals(user.getUserPassword(), out.getUserPassword());

    }

    @Test
    public void IsFileExistTestWhenFileExist() {
        User user = TestUserHendler.getUser();
        assertTrue(DataLoader.isUserExist(user));
    }

    @Test
    public void IsFileExistTestWhenFileNotExist() {
        assertFalse(DataLoader.isUserExist(new User("Gosha", "Kalosha")));
    }
}
