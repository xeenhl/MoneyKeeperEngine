package com.moneykeeper.GUI;

import com.moneykeeper.data.Input;
import com.moneykeeper.data.Type;
import com.moneykeeper.logic.Engine;
import com.moneykeeper.settings.Settings;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: admin
 * Date: 28.11.13
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */
public class MoneyKeeper extends Application {

    private final String INCOME_CHART_SIGN = "Incomes";
    private final String OUTCOME_CHART_SIGN = "Outcomes";

    private Engine engine = new Engine();

    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {

        Scene scene;

        if(engine.isUserLoggedIn()) {
            StackPane root = new StackPane();
            scene = new Scene(root, 500, 500);

            root.getChildren().add(getBaseChart());

        }else{
            VBox loginroot = new VBox();
            scene = new Scene(loginroot, Settings.LOGIN_WINDOW_H, Settings.LOGIN_WINDOW_W);

            final TextField login_field = new TextField();
            final PasswordField password = new PasswordField();
            Text loging = new Text(Settings.LOGIN);
            Text pass = new Text(Settings.PASSWORD);

            Button login_btn = new Button("Login");

            loginroot.getChildren().add(loging);
            loginroot.getChildren().add(login_field);
            loginroot.getChildren().add(pass);
            loginroot.getChildren().add(password);
            loginroot.getChildren().add(login_btn);

            login_btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    engine.Login(login_field.getText(), password.getText());
                }
            });

        }

        stage.setScene(scene);
        stage.show();


    }


    private LineChart<String, Number> getBaseChart() {
        LineChart<String, Number> chart = new LineChart<String, Number>(new CategoryAxis(), new NumberAxis());

        List<Input> incomes = engine.getInputsByType(Type.INCOME);
        List<Input> outcomes = engine.getInputsByType(Type.OUTCOME);


        XYChart.Series incomesSeries = new XYChart.Series();
        incomesSeries.setName(INCOME_CHART_SIGN);

        XYChart.Series outcomesSeries = new XYChart.Series();
        outcomesSeries.setName(OUTCOME_CHART_SIGN);

        for(Input e: incomes) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getDate());
            incomesSeries.getData().add(new XYChart.Data(new SimpleDateFormat("MMM").format(e.getDate()), e.getAmount().getAmount()));
        }

        for(Input e: outcomes) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(e.getDate());
            incomesSeries.getData().add(new XYChart.Data(new SimpleDateFormat("MMM").format(e.getDate()), e.getAmount().getAmount()));
        }

        chart.getData().addAll(incomesSeries, outcomesSeries);

        return chart;
    }
}
