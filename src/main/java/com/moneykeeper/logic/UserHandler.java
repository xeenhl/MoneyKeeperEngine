package com.moneykeeper.logic;

import com.moneykeeper.user.User;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.11.13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public class UserHandler {

    protected static User getUser(String name, String password) {

        User user;

        user = new User(name, password);


        return user;
    }
}
