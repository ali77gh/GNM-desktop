package com.gnm.desktop.core;

import com.gnm.desktop.data.DB;
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

    public static boolean checkPhoneNumberNotExist(TextField input, Label err, Customer customer) {

        if (customer == null) {
            err.setVisible(true);
            err.setText("مشتری با این شماره وجود ندارد");
            shake(err);
            setupClearError(input, err);//call this while returns false
            return false;
        }
        return true;
    }

    public static boolean checkPhoneNumberExist(TextField input, Label err) {

        if (DB.Customers.getByPhone(input.getText()) != null) {
            err.setVisible(true);
            err.setText("مشتری با این شماره وجود دارد");
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

    // configs limits here
    public static final int SERVICE_NAME = 14;
    public static final int CONSOLE_NAME = 2;
    public static final int CUSTOMER_NAME = 14;
    public static final int MONEY = 8;
    public static final int PHONE_NUMBER = 11;
    public static final int GAME_NAME = 20;

    public static void setLimit(TextField tf, int limit) {

        tf.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                // Check if the new character is greater than LIMIT
                if (tf.getText().length() >= limit) {

                    // if it's 11th character then just setText to previous
                    // one
                    tf.setText(tf.getText().substring(0, limit));
                }
            }
        });
    }
}
