package by.bsuir.saimmod.controller;

import by.bsuir.saimmod.entity.StateMachine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class MainController
{
    @FXML
    TextField p1Field;
    @FXML
    TextField p2Field;
    @FXML
    TextField n1Field;
    @FXML
    TextField tactField;
    @FXML
    TextField n2Field;
    @FXML
    LineChart pOtkChart;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        double pi1 = 0;
        double pi2 = 0;
        int n1 = 0;
        int n2 = 0;
        int tact = 0;
        try
        {
            pi1 = Double.parseDouble(p1Field.getText());
            pi2 = Double.parseDouble(p2Field.getText());
            n1 = Integer.parseInt(n1Field.getText());
            n2 = Integer.parseInt(n2Field.getText());
            tact = Integer.parseInt(tactField.getText());
        } catch (RuntimeException e)
        {

        }
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Pотк");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Pотк1");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Pотк2");
        for (int n = 0; n < 11; n++) {
            StateMachine stateMachine = new StateMachine((1F + n * 0.5), pi1, pi2, tact, n1, n2);
            stateMachine.performTact();
            series1.getData().add(new XYChart.Data(1 + n * 0.5, stateMachine.getResult().Potk));
            series2.getData().add(new XYChart.Data(1 + n * 0.5, stateMachine.getResult().Potk1));
            series3.getData().add(new XYChart.Data(1 + n * 0.5, stateMachine.getResult().Potk2));
        }
        pOtkChart.getData().add(series1);
        pOtkChart.getData().add(series2);
        pOtkChart.getData().add(series3);

    }
    @FXML
    protected void handleClearButtonAction(ActionEvent event) {
        pOtkChart.getData().setAll(new ArrayList());
    }
}
