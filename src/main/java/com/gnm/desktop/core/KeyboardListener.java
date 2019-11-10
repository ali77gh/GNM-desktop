package com.gnm.desktop.core;

import javafx.scene.Node;
import javafx.scene.input.KeyCode;


public class KeyboardListener {


    /**
     * @param okBtn           click listener will call callback
     * @param enterClickNodes list off nodes that enter action will work on focus (all inputsInDialog)
     */
    public static void setEnter(Node okBtn, EnterListenerListener cb, Node... enterClickNodes) {

        okBtn.setOnMouseClicked(mouseEvent -> cb.onEnterClick());

        for (Node n : enterClickNodes)
            n.setOnKeyPressed(ke -> {
                if (ke.getCode().equals(KeyCode.ENTER))
                    cb.onEnterClick();
            });
    }

    public interface EnterListenerListener {
        void onEnterClick();
    }
}
