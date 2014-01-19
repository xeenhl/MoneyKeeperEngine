package com.moneykeeper.settings;

import com.moneykeeper.data.Category;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 04.12.13
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class Settings {

    /*
        Application file system settings
     */

    public static final String SAVE_FOLDER = "user/";
    public static final String EXTENSION = ".data";

    //DATA_LOADER_SETTINGS
    public static final String EMPTY = "EMPTY";

    //INPUT SETTINGS
    public static final Category DEFAULT_CATEGORY = Category.NO_CATEGORY_I;

    /*
        Application GUI settings
     */

    public static final int LOGIN_WINDOW_H = 250;
    public static final int LOGIN_WINDOW_W = 300;

    public static final String LOGIN = "User Name:";
    public static final String PASSWORD = "Password:";

}
