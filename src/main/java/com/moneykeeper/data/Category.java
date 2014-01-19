package com.moneykeeper.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 26.11.13
 * Time: 16:12
 * To change this template use File | Settings | File Templates.
 */
public enum Category {

    NO_CATEGORY_I("No Category", Type.INCOME),
    NO_CATEGORY_O("No Category", Type.OUTCOME),

    INCOME_ADVANCE_PAYMENT("Payment", Type.INCOME),
    INCOME_SALARY("Salary", Type.INCOME),
    INCOME_ADDITIONAL("Additional", Type.INCOME),
    INCOME_OTHER_INCOME("Other", Type.INCOME),
    INCOME_CREDIT("Credit", Type.INCOME),

    OUTCOME_CHARGE_COMMUNAL("Communal", Type.OUTCOME),
    OUTCOME_CHARGE_COMMUNAL_GAS("Communal gas", Type.OUTCOME),
    OUTCOME_CHARGE_COMMUNAL_WATER("Communal water", Type.OUTCOME),
    OUTCOME_CHARGE_COMMUNAL_ELECTRIC("Communal electric", Type.OUTCOME),
    OUTCOME_CHARGE_COMMUNAL_OTHER("Communal Other", Type.OUTCOME),
    OUTCOME_MEDICINE("Medicine", Type.OUTCOME),
    OUTCOME_ENTERTAIMENT("Entertaiment", Type.OUTCOME),
    OUTCOME_TRANSPORT("Transport", Type.OUTCOME),
    OUTCOME_TRANSPORT_PUBLIC("Public Transport", Type.OUTCOME),
    OUTCOME_TRANSPORT_TAXI("Taxi", Type.OUTCOME),
    OUTCOME_TRANSPORT_PLANE("Plane", Type.OUTCOME),
    OUTCOME_TRANSPORT_CAR("Car", Type.OUTCOME),
    OUTCOME_GOODS("Goods", Type.OUTCOME),
    OUTCOME_GOODS_FOOD("Food", Type.OUTCOME),
    OUTCOME_CREDIT("Credit payment", Type.OUTCOME);

    private String name;
    private Type type;

    Category(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    public static List<Category> getCategoriesByType(Type t) {

        List<Category> output = new ArrayList<>();

        for(Category c: Category.values()) {
            if(c.getType().equals(t))
                output.add(c);
        }

        return output;
    }

    public static Category getCategoryByName(String n) {

        for(Category c: Category.values()) {
            if(c.getName().equals(n))
                return c;
        }

       return null;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

}
