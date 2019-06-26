package com.gnm.desktop.ui.layout;

import com.gnm.desktop.res.Color;
import com.gnm.desktop.ui.view.MenuItem;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class RightMenu extends VBox {

    private List<MenuItem> menus;

    private String style =
            "-fx-background-color:"+ Color.darkGray + ";";

    public RightMenu(RightMenuCallback callback) {

        //styling

        setStyle(style);
        setPadding(new Insets(30, 0, 0, 0));

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
            SetSelect(home);
            callback.onMenuSelect(HOME);
        });

        monitor.setOnMouseClicked(mouseEvent -> {
            SetSelect(monitor);
            callback.onMenuSelect(MONITOR);
        });

        prices.setOnMouseClicked(mouseEvent -> {
            SetSelect(prices);
            callback.onMenuSelect(PRICES);
        });

        games.setOnMouseClicked(mouseEvent -> {
            SetSelect(games);
            callback.onMenuSelect(GAMES);
        });

        customers.setOnMouseClicked(mouseEvent -> {
            SetSelect(customers);
            callback.onMenuSelect(CUSTOMER);
        });

        settings.setOnMouseClicked(mouseEvent -> {
            SetSelect(settings);
            callback.onMenuSelect(SETTINGS);
        });

        about.setOnMouseClicked(mouseEvent -> {
            SetSelect(about);
            callback.onMenuSelect(ABOUT_US);
        });
    }

    private void SetSelect(MenuItem selectedMenu) {

        for (MenuItem menu : menus) {
            menu.setSelect(false);
        }
        selectedMenu.setSelect(true);
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
