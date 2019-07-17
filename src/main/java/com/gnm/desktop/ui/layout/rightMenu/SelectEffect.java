package com.gnm.desktop.ui.layout.rightMenu;

import com.gnm.desktop.res.css.CSSStyler;
import com.gnm.desktop.ui.animation.ElasticInterpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

class SelectEffect extends VBox {

    private Label effect;
    private TranslateTransition tt;

    public SelectEffect(){

        //load css file
        getStylesheets().add(CSSStyler.get("app.css"));


        effect=new Label();
        //effect styles
        effect.getStyleClass().add("selecteffect_effect");

        //class styles
        getStyleClass().add("selecteffect");

        //adding nodes to rootLayout
        getChildren().add(effect);


        //setting padding to set default position for first right menu item
        //top padding is measured by (rightmenu.Items.TopPadding)-40
        setPadding(new Insets(60,0,0,0));


        //animation
        tt=new TranslateTransition();

    }

    void select(int menu){
        tt.setToY(menu*55);
        tt.setDuration(Duration.millis(450));
        tt.setNode(effect);
        tt.setInterpolator(new ElasticInterpolator());
        tt.play();
    }

}
