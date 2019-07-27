package com.gnm.desktop.ui.layout.rightMenu;

import com.gnm.desktop.ui.view.MenuItem;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Items extends Pane {

    private List<MenuItem> menus;
    private VBox vbox;


    public Items(RightMenuCallback callback) {
        //spacing
        vbox =new VBox(5);
        vbox.setPadding(new Insets(100, 20, 10, 0));


        //add vbox to root Pane layout
        getChildren().add(vbox);


        //add buttons

        MenuItem home = new MenuItem(HOME);
        home.setPrefWidth(230);//this line makes it work corectly dont know why!!
        MenuItem monitor = new MenuItem(MONITOR);
        MenuItem prices = new MenuItem(PRICES);
        MenuItem customers = new MenuItem(CUSTOMER);
        MenuItem settings = new MenuItem(SETTINGS);
        MenuItem about = new MenuItem(ABOUT_US);

        menus = new ArrayList<>();
        menus.add(home);
        menus.add(monitor);
        menus.add(prices);
        menus.add(customers);
        menus.add(settings);
        menus.add(about);


        vbox.getChildren().addAll(menus);

        //click listeners
        home.setOnMouseClicked(mouseEvent -> callback.onMenuSelect(HOME));

        monitor.setOnMouseClicked(mouseEvent -> callback.onMenuSelect(MONITOR));

        prices.setOnMouseClicked(mouseEvent -> callback.onMenuSelect(PRICES));

        customers.setOnMouseClicked(mouseEvent -> callback.onMenuSelect(CUSTOMER));

        settings.setOnMouseClicked(mouseEvent -> callback.onMenuSelect(SETTINGS));

        about.setOnMouseClicked(mouseEvent -> callback.onMenuSelect(ABOUT_US));
    }



    public interface RightMenuCallback {
        void onMenuSelect(int menu);
    }

    //indexes are important
    public static final int HOME = 0;
    public static final int MONITOR = 1;
    public static final int PRICES = 2;
    public static final int CUSTOMER = 3;
    public static final int SETTINGS = 4;
    public static final int ABOUT_US = 5;
}
