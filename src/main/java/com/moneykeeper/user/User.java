package com.moneykeeper.user;

import com.moneykeeper.data.Input;
import com.moneykeeper.data.Money;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 27.11.13
 * Time: 10:56
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("User")
public class User{

    @XStreamAlias("Name")
    private final String name;
    @XStreamAlias("Password")
    private final String password;

    @XStreamImplicit
    Set<Input> inputs = new HashSet<Input>();

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getUserName() {
        return name;
    }

    public String getUserPassword() {
        return password;
    }

    public Set<Input> getInputs() {

        return inputs;
    }

    public void updateInputs(Set<Input> inputs) {
        this.inputs = inputs;
    }


}
