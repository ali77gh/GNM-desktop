package com.gnm.desktop.ui.view;


import javafx.scene.control.Label;

public class GameTag extends Label {

    public GameTag(String name){
        setText(name);

        getStyleClass().add("customerCard_lblgameTags");
    }

}
