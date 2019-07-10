package com.gnm.desktop.ui.layout.rightMenu;

import com.gnm.desktop.ui.view.MenuItem;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Items extends VBox {

    private List<MenuItem> menus;


    public Items(RightMenuCallback callback) {
        //spacing
        super(5);

        //if you want to add more top padding you must increse minwindow height
        setPadding(new Insets(80, 10, 10, 0));


        //add buttons

        MenuItem home = new MenuItem(HOME);
        MenuItem monitor = new MenuItem(MONITOR);
        MenuItem prices = new MenuItem(PRICES);
        MenuItem games = new MenuItem(GAMES);
        MenuItem customers = new MenuItem(CUSTOMER);
        MenuItem settings = new MenuItem(SETTINGS);
        MenuItem about = new MenuItem(ABOUT_US);

        menus = new ArrayList<>();
        menus.add(home);
        menus.add(monitor);
        menus.add(prices);
        menus.add(games);
        menus.add(customers);
        menus.add(settings);
        menus.add(about);


        getChildren().addAll(menus);

        //click listeners

        home.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(HOME);
        });

        monitor.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(MONITOR);
        });

        prices.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(PRICES);
        });

        games.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(GAMES);
        });

        customers.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(CUSTOMER);
        });

        settings.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(SETTINGS);
        });

        about.setOnMouseClicked(mouseEvent -> {
            callback.onMenuSelect(ABOUT_US);
        });
    }


    public interface RightMenuCallback {
        void onMenuSelect(int menu);
    }

    public static final int HOME = 0;
    public static final int MONITOR = 1;
    public static final int PRICES = 2;
    public static final int GAMES = 3;
    public static final int CUSTOMER = 4;
    public static final int SETTINGS = 5;
    public static final int ABOUT_US = 6;
}
