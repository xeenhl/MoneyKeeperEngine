package com.moneykeeper.data;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 26.11.13
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public enum Type {

    INCOME("Income"), OUTCOME("Outcome");

    private String string;

    Type(String type) {
        string = type;
    }

    public String getName() {
        return string;
    }

    public static Type getTypeByName(String name) {

        for( Type e: Type.values()) {
            if(name.equals(e.getName()))
                return e;
        }

        return null;
    }
}
