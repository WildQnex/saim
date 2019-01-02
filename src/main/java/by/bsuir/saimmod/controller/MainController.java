package by.bsuir.saimmod.controller;

import java.util.Random;

import by.bsuir.saimmod.entity.Message;
import by.bsuir.saimmod.entity.StateMachine;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainController
{
    Random random = new Random();
    @FXML
    TextField pField;
    @FXML
    TextField p1Field;
    @FXML
    TextField p2Field;
    @FXML
    TextField p3Field;
    @FXML
    TextField tactField;
    @FXML
    TextField aField;
    @FXML
    TextField wCField;
    @FXML
    TextField pOtkField;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        double p = 0;
        double pi1 = 0;
        double pi2 = 0;
        double pi3 = 0;
        int tact = 0;
        try
        {
            p = Double.parseDouble(pField.getText());
            pi1 = Double.parseDouble(p1Field.getText());
            pi2 = Double.parseDouble(p2Field.getText());
            pi3 = Double.parseDouble(p3Field.getText());
            tact = Integer.parseInt(tactField.getText());
        } catch (RuntimeException e)
        {

        }
        StateMachine stateMachine = new StateMachine(p, pi1, pi2, pi3);
        for (int i = 0; i < tact; i++)
        {
            stateMachine.performTact();
        }
        double A = ((double)stateMachine.getMessages().stream().filter(Message::isProcessed).count())/tact;
        aField.setText(Double.toString(A));
        double Wc = ((double)stateMachine.getMessages().stream().filter(Message::isProcessed).map(Message::getTactsAmount).reduce((a, b) -> a+b).get())/stateMachine.getMessages().stream().filter(Message::isProcessed).count();
        wCField.setText(Double.toString(Wc));
        float pOtk = ((float)stateMachine.getMessages().stream().filter(Message::isProcessed).count())/stateMachine.getMessages().size();
        pOtkField.setText((1F - pOtk) + "");
    }
}
