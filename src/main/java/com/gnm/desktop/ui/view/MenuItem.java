package com.gnm.desktop.ui.view;

import com.gnm.desktop.ui.layout.rightMenu.Items;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;


public class MenuItem extends HBox {

    private Label name;
    private Label icon;


    public MenuItem(int menu){
        //spacing
        super(40);

        setPrefHeight(50);
        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(10, 10, 10, 20));


        name = new Label();
        icon = new Label();

        //add views
        getChildren().addAll(name,icon);

        //style
        icon.setPrefWidth(24);
        icon.setPrefHeight(24);
        icon.getStyleClass().add("menuItem_icon");


        //font size style
        name.getStyleClass().addAll("size18");

        //load icon and name
        switch (menu) {
            case Items.HOME:
                name.setText("خانه");
                icon.getStyleClass().add("homeIcon");
                break;
            case Items.MONITOR:
                name.setText("گزارش");
                icon.getStyleClass().add("monitorIcon");
                break;
            case Items.PRICES:
                name.setText("قیمت ها");
                icon.getStyleClass().add("pricesIcon");
                break;
            case Items.CUSTOMER:
                name.setText("مشتریان");
                icon.getStyleClass().add("customerIcon");
                break;
            case Items.ABOUT_US:
                name.setText("درباره ما");
                icon.getStyleClass().add("about_usIcon");
                break;
            default:
                throw new RuntimeException("invalid menu:" + menu);
        }
    }
}
