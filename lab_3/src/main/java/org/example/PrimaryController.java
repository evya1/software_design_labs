package org.example;

import java.util.ArrayList;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

public class PrimaryController {
    private String currentExpression = "";
    private int currentBase;
    private boolean actionButtonsFlag = false;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> listBox;

    @FXML
    private TextField resultTF;

    @FXML
    private Button oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn, zeroBtn,
            plusBtn, minusBtn, multBtn, divBtn, resultBtn, clearBtn,
            aHexaBtn, bHexaBtn, cHexaBtn, dHexaBtn, eHexaBtn, fHexaBtn;

    @FXML
    private ArrayList<Button> buttonList;

    @FXML
    private ArrayList<Button> actionButtonList;


    @FXML
    void chooseBase(ActionEvent event) {
        currentBase = listBox.getSelectionModel().getSelectedItem();
        if (!actionButtonsFlag) {
            int i = 0;
            for (; i < actionButtonList.size(); i++) {
                actionButtonList.get(i).setDisable(false);
            }
            actionButtonsFlag = true;
        }
        updateButtons(currentBase);
        if(currentExpression != ""){
            result(event);
        }
    }

    @FXML
    void initialize() {
        assert listBox != null : "fx:id=\"listBox\" was not injected: check your FXML file 'primary.fxml'.";
        listBox.getItems().addAll(2, 8, 10, 16);
        actionButtonList = new ArrayList<>(Arrays.asList(plusBtn, minusBtn, multBtn, divBtn, resultBtn));
        buttonList = new ArrayList<>(Arrays.asList(zeroBtn, oneBtn, twoBtn, threeBtn, fourBtn, fiveBtn, sixBtn, sevenBtn, eightBtn, nineBtn,
                aHexaBtn, bHexaBtn, cHexaBtn, dHexaBtn, eHexaBtn, fHexaBtn));
    }

    private void updateButtons(int base) {
        int i = 0;
        for (; i < base; i++) {
            buttonList.get(i).setDisable(false);
        }
        int j = buttonList.size() - 1;
        for (; j >= base; j--) {
            buttonList.get(j).setDisable(true);
        }
    }

    @FXML
    void addToExpression(ActionEvent event) {
        Button btn = (Button) event.getSource();
        currentExpression += btn.getText();
        resultTF.setText(currentExpression.toString());
    }

    @FXML
    void clear(ActionEvent event) {
        resultTF.clear();
        currentExpression = "";
    }

    @FXML
    void result(ActionEvent event) {
        ArrayList<String> expressionParts = new ArrayList<>();
        ArithmeticApp.isValidExpression(currentExpression, currentBase, expressionParts);
        try {
            double result = ArithmeticApp.solve(expressionParts, currentBase);
            currentExpression = String.valueOf(result);
            resultTF.setText(currentExpression);
        } catch (ArithmeticException e) {
            resultTF.setText("Invalid Expression");
            currentExpression = "";
        }
    }
}