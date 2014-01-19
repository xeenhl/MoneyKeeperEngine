package com.moneykeeper.logic;

import com.moneykeeper.data.Category;
import com.moneykeeper.data.Input;
import com.moneykeeper.data.Type;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 26.11.13
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */
public interface GeneralEngine {

    public boolean Login(String userName, String userPass);
    public boolean Logoff();

    public void addInput(Input input);
    public void updateInput(Input input);
    public List<Input> getInputsByType(Type type);
    public List<Input> getInputsByCatecory(Category category);

}
