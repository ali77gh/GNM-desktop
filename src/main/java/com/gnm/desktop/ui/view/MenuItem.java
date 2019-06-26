package com.gnm.desktop.ui.view;

import com.gnm.desktop.core.Log;
import com.gnm.desktop.res.icon.Icon;
import com.gnm.desktop.res.Color;
import com.gnm.desktop.ui.layout.RightMenu;
import com.sun.javafx.beans.event.AbstractNotifyListener;
import javafx.beans.Observable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;


public class MenuItem extends HBox {

    private final String selectedStyle =
            "-fx-background-color:"+ Color.darkerGray + ";";

    private final String deselectedStyle =
            "-fx-background-color:"+ Color.darkGray + ";";

    public MenuItem(int menu){
        super(10);//spacing

        //add views
        Label name = new Label();
        ImageView icon = new ImageView();
        getChildren().addAll(name,icon);

        //style
        setAlignment(Pos.CENTER_RIGHT);
        setPadding(new Insets(10, 20, 10, 20));
        icon.setFitWidth(25);
        icon.setFitWidth(25);
        name.setTextFill(Paint.valueOf(Color.white));
        setStyle(deselectedStyle);


        //load icon and name
        switch (menu) {
            case RightMenu.HOME:
                setStyle(selectedStyle); //selected by default
                name.setText("خانه");
                icon.setImage(Icon.get("menu_home.png"));
                break;
            case RightMenu.MONITOR:
                name.setText("دخل و خرج");
                icon.setImage(Icon.get("menu_chart.png"));
                break;
            case RightMenu.PRICES:
                name.setText("قیمت ها");
                icon.setImage(Icon.get("menu_prices.png"));
                break;
            case RightMenu.GAMES:
                name.setText("بازی ها");
                icon.setImage(Icon.get("menu_games.png"));
                break;
            case RightMenu.CUSTOMER:
                name.setText("مشتری");
                icon.setImage(Icon.get("menu_customer.png"));
                break;
            case RightMenu.SETTINGS:
                name.setText("تنظیمات");
                icon.setImage(Icon.get("menu_settings.png"));
                break;
            case RightMenu.ABOUT_US:
                name.setText("درباره ما");
                icon.setImage(Icon.get("menu_about_us.png"));
                break;
            default:
                throw new RuntimeException("invalid menu:" + menu);
        }
    }

    public void setSelect(boolean selected){
        if (selected)
            setStyle(selectedStyle);
        else
            setStyle(deselectedStyle);
    }
}
