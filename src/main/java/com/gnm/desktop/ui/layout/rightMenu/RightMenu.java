package com.gnm.desktop.ui.layout.rightMenu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class RightMenu extends StackPane implements Items.RightMenuCallback{

    private Items.RightMenuCallback callback;
    private SelectEffect selectEffect;

    public RightMenu(Items.RightMenuCallback callback){

        //right menu width
        setPrefWidth(240);

        setAlignment(Pos.BOTTOM_CENTER);

        this.callback = callback;
        selectEffect = new SelectEffect();
        getChildren().addAll(
                selectEffect,
                new Items(this),
                getAppName(),
                getVersion()
        );
    }

    private Node getAppName() {
        Label name = new Label("snack");
        name.getStyleClass().addAll("dialogText", "size18");
        StackPane.setAlignment(name, Pos.TOP_CENTER);
        StackPane.setMargin(name, new Insets(40, 0, 0, 0));

        return name;
    }

    private Node getVersion() {
        Label name = new Label("نسخه 0.9.0");
        name.getStyleClass().addAll("dialogText");
        StackPane.setAlignment(name, Pos.BOTTOM_CENTER);
        StackPane.setMargin(name, new Insets(0, 0, 30, 0));

        return name;
    }

    @Override
    public void onMenuSelect(int menu) {
        callback.onMenuSelect(menu);
        selectEffect.select(menu);
    }
}
