package com.gnm.desktop.core;

import com.gnm.desktop.data.model.Customer;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class Validation {

    public static boolean checkEmpty(TextField input, Label err) {

        if (input.getText().equals("")) {
            err.setVisible(true);
            err.setText("خالی است");
            shake(err);
            setupClearError(input, err);//call this while returns false
            return false;
        }
        return true;
    }

    public static boolean checkEmptyAndNumeric(TextField input, Label err) {

        if (input.getText().equals("")) {
            err.setVisible(true);
            err.setText("خالی است");
            shake(err);
            setupClearError(input, err);//call this while returns false
            return false;
        } else if (!isInt(input.getText())) {
            err.setVisible(true);
            err.setText("عدد وارد کنید");
            shake(err);
            setupClearError(input, err);//call this while returns false
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumberExist(TextField input, Label err, Customer customer) {

        if (customer == null) {
            err.setVisible(true);
            err.setText("مشتری با این شماره وجود ندارد");
            shake(err);
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


    private static void shake(Node node) {

        TranslateTransition tt = new TranslateTransition(Duration.millis(100));
        tt.stop();
        tt.setNode(node);
        tt.setToX(20);
        tt.setFromX(0);
        tt.setCycleCount(6);
        tt.setAutoReverse(true);
        tt.playFromStart();
    }
}
