package com.gnm.desktop.ui.layout.rightMenu;

import com.gnm.desktop.ui.view.MenuItem;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Items extends Pane {

    private int selected = 0;

    Items(RightMenuCallback callback) {
        //spacing
        VBox vbox = new VBox(5);
        vbox.setPadding(new Insets(100, 20, 10, 0));


        //add vbox to root Pane layout
        getChildren().add(vbox);


        //add buttons

        MenuItem home = new MenuItem(HOME);
        home.setPrefWidth(230);//this line makes it work corectly dont know why!!
        MenuItem monitor = new MenuItem(MONITOR);
        MenuItem prices = new MenuItem(PRICES);
        MenuItem customers = new MenuItem(CUSTOMER);
        MenuItem about = new MenuItem(ABOUT_US);

        List<MenuItem> menus = new ArrayList<>();
        menus.add(home);
        menus.add(monitor);
        menus.add(prices);
        menus.add(customers);
        menus.add(about);


        vbox.getChildren().addAll(menus);

        //click listeners
        home.setOnMouseClicked(mouseEvent -> select(HOME, callback));

        monitor.setOnMouseClicked(mouseEvent -> select(MONITOR, callback));

        prices.setOnMouseClicked(mouseEvent -> select(PRICES, callback));

        customers.setOnMouseClicked(mouseEvent -> select(CUSTOMER, callback));

        about.setOnMouseClicked(mouseEvent -> select(ABOUT_US, callback));
    }

    private void select(int index, RightMenuCallback callback) {
        if (selected == index) return;
        callback.onMenuSelect(index);
        selected = index;
    }


    public interface RightMenuCallback {
        void onMenuSelect(int menu);
    }

    //indexes are important
    public static final int HOME = 0;
    public static final int MONITOR = 1;
    public static final int PRICES = 2;
    public static final int CUSTOMER = 3;
    public static final int ABOUT_US = 4;
}
