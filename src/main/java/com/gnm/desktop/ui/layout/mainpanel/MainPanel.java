package com.gnm.desktop.ui.layout.mainpanel;

import com.gnm.desktop.res.css.CSSStyler;
import com.gnm.desktop.ui.layout.*;
import com.gnm.desktop.ui.layout.rightMenu.Items;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;


//mainpanel that stores toolbar and mainlayouts which changes by selecting rightmenu items
public class MainPanel extends AnchorPane {

    private Group mainPane;
    private Toolbar toolbar;

    public MainPanel() {

        //load css files
        getStylesheets().add(CSSStyler.get("BackgroundColor.css"));


        //class styles
        getStyleClass().add("red");


        setPrefHeight(Double.MAX_VALUE);


        toolbar=new Toolbar();
        AnchorPane.setTopAnchor(toolbar,0.0);
        AnchorPane.setLeftAnchor(toolbar,0.0);
        AnchorPane.setRightAnchor(toolbar,0.0);


        mainPane=new Group();
        AnchorPane.setTopAnchor(mainPane,50.0);
        AnchorPane.setLeftAnchor(mainPane,0.0);
        AnchorPane.setRightAnchor(mainPane,0.0);
        AnchorPane.setBottomAnchor(mainPane,0.0);


        getChildren().add(toolbar);
        getChildren().add(mainPane);


        //default layout
        mainPane.getChildren().add(new HomeLayout());


    }

    public void setLayout(int menu) {

        //clear current layout
        mainPane.getChildren().remove(0);

        //load new layout
        switch (menu){
            case Items.HOME:
                mainPane.getChildren().add(new HomeLayout());
                break;
            case Items.MONITOR:
                mainPane.getChildren().add(new MonitorLayout());
                break;
            case Items.PRICES:
                mainPane.getChildren().add(new PricesLayout());
                break;
            case Items.GAMES:
                mainPane.getChildren().add(new GamesLayout());
                break;
            case Items.CUSTOMER:
                mainPane.getChildren().add(new CustomerLayout());
                break;
            case Items.SETTINGS:
                mainPane.getChildren().add(new SettingsLayout());
                break;
            case Items.ABOUT_US:
                mainPane.getChildren().add(new AboutUsLayout());
                break;
            default:
                throw new RuntimeException("invalid menu:" + menu);
        }
    }
}
