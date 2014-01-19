package com.moneykeeper.logic;

import com.moneykeeper.settings.Settings;
import com.moneykeeper.user.User;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 04.12.13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
public class DataLoader{

    private static XStream stream = new XStream();
    private static String output;

    static {
        stream.processAnnotations(User.class);
    }

    public static void save(User user) {


        final String saveData = stream.toXML(user);
        final String fileName = user.getUserName();

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            File fileToSave = new File(Settings.SAVE_FOLDER);

            if(!fileToSave.exists()) {

                try{
                    fileToSave.mkdirs();
                    fileToSave = new File(Settings.SAVE_FOLDER + fileName+Settings.EXTENSION);
                    fileToSave.createNewFile();
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(fileToSave.exists()) {
                try (PrintWriter out = new PrintWriter(Settings.SAVE_FOLDER + fileName+Settings.EXTENSION)) {

                    out.println(saveData);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            }
        });

        t.start();
    }

    public static User load(User user){

        String xmlShema = Settings.EMPTY;
        User output;

        if(isUserExist(user)) {

            try {

                byte[] encoded = Files.readAllBytes(Paths.get(Settings.SAVE_FOLDER + user.getUserName()+Settings.EXTENSION));

                xmlShema = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();

            }catch (Exception e) {
                e.printStackTrace();
            }

            if(!xmlShema.equals(Settings.EMPTY))
                output = (User)stream.fromXML(xmlShema);
            else {
                return null;
            }
        }else {
            System.err.println("File not exist");
            return null;
        }

        return output;
    }

    public static boolean isUserExist(User user) {

        if(new File(Settings.SAVE_FOLDER + user.getUserName()+Settings.EXTENSION).exists())
            return true;
        return false;
    }

    public static User openFile(File file) {

        String xmlShema = Settings.EMPTY;
        User output;

        try {

            byte[] encoded = Files.readAllBytes(Paths.get(file.getAbsolutePath()));

            xmlShema = Charset.defaultCharset().decode(ByteBuffer.wrap(encoded)).toString();

        }catch (Exception e) {
            e.printStackTrace();
        }

        if(!xmlShema.equals(Settings.EMPTY))
            output = (User)stream.fromXML(xmlShema);
        else {
            return null;
        }
        return output;
    }

    private static void setOut(String out) {
        output = out;
    }

    private static String getOut() {
        if(output != null)
            return output;
        else
            return null;
    }

}
