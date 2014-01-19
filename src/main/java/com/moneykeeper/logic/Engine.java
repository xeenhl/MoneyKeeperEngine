package com.moneykeeper.logic;

import com.moneykeeper.data.Category;
import com.moneykeeper.data.Input;
import com.moneykeeper.data.Money;
import com.moneykeeper.data.Type;
import com.moneykeeper.user.User;

import java.io.File;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 27.11.13
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class Engine implements GeneralEngine {

    private User user;
    private boolean loggedin = false;

    public Engine() {

    }

    @Override
    public boolean Login(String userName, String userPass) {

        user = UserHandler.getUser(userName, userPass);

        if(DataLoader.isUserExist(user)) {
            this.user = DataLoader.load(user);
            loggedin = true;
            return true;
        }else{
            loggedin = false;
            return false;  //To change body of implemented methods use File | Settings | File Templates.

        }
    }

    @Override
    public boolean Logoff() {


        return false;
    }

    @Override
    public void addInput(Input input) {

        if(user.getInputs() == null) {

            Set<Input> newInputs = new HashSet<Input>();
            newInputs.add(input);
            user.updateInputs(newInputs);
            return;

        }

        if(!isInputExist(input))
            updateInput(input);

        Set<Input> newInputs = user.getInputs();
        newInputs.add(input);
        user.updateInputs(newInputs);

    }

    @Override
    public void updateInput(Input input) {

        if(isInputExist(input))
            addInput(input);

        Set<Input> newInputs = user.getInputs();
        for(Input e: newInputs) {
            if(e.getId().equals(input.getId()))
                newInputs.remove(e);
                break;
        }
        newInputs.add(input);
        user.updateInputs(newInputs);

    }

    public void updateInputs(List<Input> inputs) {

        Set<Input> newInputs = new HashSet<>();

        for(Input e: inputs) {
            newInputs.add(e);
        }

        user.updateInputs(newInputs);
    }

    public List<Input> getAllInputs() {

        if(user.getInputs() == null)
            return null;

        List<Input> output = new LinkedList<Input>();

        Set<Input> inputs = user.getInputs();

        for(Input e: inputs)
            output.add(e);

        return output;
    }

    @Override
    public List<Input> getInputsByType(Type type) {

        List<Input> output = new ArrayList<Input>();

        Set<Input> inputs = user.getInputs();

        for(Input e: inputs) {
            if(e.getType().equals(type))
                output.add(e);
        }

        return output;

    }

    @Override
    public List<Input> getInputsByCatecory(Category category) {

        List<Input> output = new ArrayList<Input>();

        Set<Input> inputs = user.getInputs();

        for(Input e: inputs) {
            if(e.getCategory().equals(category))
                output.add(e);
        }

        return output;
    }

    public Input getInputById(UUID id) {

        Set<Input> inputs = user.getInputs();

        for(Input e: inputs) {

            if(e.getId().equals(id))
                return e;
        }
        return null;
    }

    public boolean createNewUser(String userName, String userPass) {

        this.user = new User(userName, userPass);
        if(user != null) {
            loggedin = true;
            return true;
        }else{
            return false;
        }
    }

    public void saveData() {

        DataLoader.save(user);

    }

    public Money getDebet() {

        if(user.getInputs() == null)
            return new Money(0.0, Currency.getInstance(Locale.getDefault()));

        if(user != null) {
            Money debet = new Money(0, Currency.getInstance(Locale.getDefault()));
            Money credit = new Money(0, Currency.getInstance(Locale.getDefault()));

            Set<Input> inputs = user.getInputs();

            for(Input e: inputs) {
                if(e.getType().equals(Type.INCOME)){
                    try{
                        debet = debet.add(e.getAmount());
                    }catch (Exception exeption){
                        exeption.printStackTrace();
                    }
                }else{
                    try{
                        credit = credit.add(e.getAmount());
                    }catch (Exception exeption){
                        exeption.printStackTrace();
                    }
                }

            }

            try{
                debet = debet.subtract(credit);
            }catch (Exception e){
                e.printStackTrace();
            }

            return debet;

        }

        return null;
    }

    public String getUserName() {
        return user.getUserName();
    }

    public boolean isUserLoggedIn() {
        return loggedin;
    }

    public void openUser(File file) {
        user = DataLoader.openFile(file);
        if(user != null)
            loggedin = true;
        else
            loggedin = false;
    }

    private boolean isInputExist(Input input) {

        if(user.getInputs() == null)
            return false;

        Set<Input> inp = user.getInputs();

        for(Input e: inp) {
            if(e.getId().equals(input.getId()))
                return true;
        }

        return false;
    }
}
