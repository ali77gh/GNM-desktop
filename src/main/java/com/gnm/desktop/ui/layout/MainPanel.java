package com.gnm.desktop.ui.layout;

import com.gnm.desktop.res.Color;
import javafx.scene.layout.VBox;

public class MainPanel extends VBox {

    private String style =
            "-fx-background-color:"+ Color.darkerGray + ";";

    public MainPanel() {

        super(1);
        setStyle(style);
        setPrefHeight(Double.MAX_VALUE);


    }

    public void setLayout(int menu) {

        switch (menu){
            case RightMenu.HOME:
                //todo load layout
                break;
            case RightMenu.MONITOR:
                //todo load layout
                break;
            case RightMenu.PRICES:
                //todo load layout
                break;
            case RightMenu.GAMES:
                //todo load layout
                break;
            case RightMenu.CUSTOMER:
                //todo load layout
                break;
            case RightMenu.SETTINGS:
                //todo load layout
                break;
            case RightMenu.ABOUT_US:
                //todo load layout
                break;
            default:
                throw new RuntimeException("invalid menu:" + menu);
        }
    }
}
