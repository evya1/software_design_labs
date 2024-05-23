package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class PrimaryController {
    private String currentExpression = "";
    private int currentBase;
    private boolean actionButtonsFlag = false;
    private int result;

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
        int oldBase = currentBase;
        currentBase = listBox.getSelectionModel().getSelectedItem();

        // Activate action buttons if not already activated
        if (!actionButtonsFlag) {
            for (Button button : actionButtonList) {
                button.setDisable(false);
            }
            actionButtonsFlag = true;
        }

        // Update button activation based on the new base
        updateButtons(currentBase);

        // Check if there's a result or expression
        if (!currentExpression.isEmpty()) {
            if (oldBase != currentBase) {
                // If there's a result, format it to the new base
                try {
                    int result = Integer.parseInt(resultTF.getText(), oldBase);
                    resultTF.setText(Integer.toString(result, currentBase).toUpperCase());
                } catch (NumberFormatException e) {
                    resultTF.setText("Invalid Result");
                }
            } else {
                // If there's an expression waiting to be solved
                result(event);
            }
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
        resultTF.setText(currentExpression);
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
            double solvedResult = ArithmeticApp.solve(expressionParts, currentBase);
            int integerResult = (int) solvedResult;
            String resultInBase = Integer.toString(integerResult, currentBase).toUpperCase();
            resultTF.setText(resultInBase);
            currentExpression = resultInBase;
        } catch (ArithmeticException e) {
            resultTF.setText("Invalid Expression");
            currentExpression = "";
        }
    }
}
