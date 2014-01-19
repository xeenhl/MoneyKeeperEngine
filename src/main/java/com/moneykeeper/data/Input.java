package com.moneykeeper.data;

import com.moneykeeper.settings.Settings;

import java.util.Date;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 26.11.13
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
public class Input{

    private Money amount;
    private Type type;
    private Category category = Settings.DEFAULT_CATEGORY;
    private final Date date;
    private final UUID id;
    private String commentary;

    public Input(Money amount, Type type, Category category) {

        date = new Date();
        this.amount = amount;
        this.type = type;
        this.category = category;

        id = UUID.randomUUID();

    }

    public Input(Money amount, Type type) {

        date = new Date();
        this.amount = amount;
        this.type = type;

        id = UUID.randomUUID();

    }

    public Input(Money amount, Type type, Category category, String comment)  {

        date = new Date();
        this.amount = amount;
        this.type = type;
        this.category = category;
        this.commentary = comment;

        id = UUID.randomUUID();


    }

    public UUID getId() {
        return id;
    }

    public Money getAmount() {
        return amount;
    }

    public void updateAmount(Money newAmount) {
        amount = newAmount;
    }

    public Type getType() {
        return type;
    }

    public void changeType(Type newType) {
        type = newType;
    }

    public Category getCategory() {
        return category;
    }

    public void changeCategory(Category newCategory) {
        category = newCategory;
    }

    public void updateComment(String comment) {
        commentary = comment;
    }

    public String getComent() {
        return commentary;
    }

    public Date getDate() {
        return date;
    }

}
