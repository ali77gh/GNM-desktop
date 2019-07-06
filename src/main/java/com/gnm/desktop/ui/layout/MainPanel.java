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


        //default layout
        getChildren().add(new HomeLayout());
    }

    public void setLayout(int menu) {

        //clear current layout
        getChildren().remove(0);

        //load new layout
        switch (menu){
            case RightMenu.HOME:
                getChildren().add(new HomeLayout());
                break;
            case RightMenu.MONITOR:
                getChildren().add(new MonitorLayout());
                break;
            case RightMenu.PRICES:
                getChildren().add(new PricesLayout());
                break;
            case RightMenu.GAMES:
                getChildren().add(new GamesLayout());
                break;
            case RightMenu.CUSTOMER:
                getChildren().add(new CustomerLayout());
                break;
            case RightMenu.SETTINGS:
                getChildren().add(new SettingsLayout());
                break;
            case RightMenu.ABOUT_US:
                getChildren().add(new AboutUsLayout());
                break;
            default:
                throw new RuntimeException("invalid menu:" + menu);
        }
    }
}
