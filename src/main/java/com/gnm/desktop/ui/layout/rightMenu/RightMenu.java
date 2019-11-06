package com.gnm.desktop.ui.layout.rightMenu;

import javafx.scene.layout.StackPane;

public class RightMenu extends StackPane implements Items.RightMenuCallback{

    private Items.RightMenuCallback callback;
    private SelectEffect selectEffect;

    public RightMenu(Items.RightMenuCallback callback){

        //right menu width
        setPrefWidth(240);


        this.callback = callback;
        selectEffect = new SelectEffect();
        getChildren().addAll(selectEffect,new Items(this));


    }

    @Override
    public void onMenuSelect(int menu) {
        callback.onMenuSelect(menu);
        selectEffect.select(menu);
    }
}
