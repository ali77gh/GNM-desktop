package com.gnm.desktop.core;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Validation {

    public static boolean checkEmpty(TextField input, Label err) {

        if (input.getText().equals("")) {
            err.setVisible(true);
            err.setText("خالی است");
            setupClearError(input, err);//call this while returns false
            return false;
        }
        return true;
    }

    public static boolean checkEmptyAndNumeric(TextField input, Label err) {

        if (input.getText().equals("")) {
            err.setVisible(true);
            err.setText("خالی است");
            setupClearError(input, err);//call this while returns false
            return false;
        } else if (!isInt(input.getText())) {
            err.setVisible(true);
            err.setText("عدد وارد کنید");
            setupClearError(input, err);//call this while returns false
            return false;
        }
        return true;
    }

    private static boolean isInt(String str) {
        try {
            Long.valueOf(str);
            return true;
        } catch (java.lang.NumberFormatException e) {
            return false;
        }
    }

    //clear err when user start typing
    private static void setupClearError(TextField input, Label err) {
        input.textProperty().addListener(observable -> err.setVisible(false));
    }
}
