package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CBSCard extends VBox{

    private CBSListItem cbsListItem;

    public CBSCard(){
        super(2);
        getStylesheets().add(CSSStyler.get("app.css"));


        cbsListItem=new CBSListItem();

        //add addCardTBS
        setPadding(new Insets(10,10,10,10));
        setPrefSize(450, 700);
        getStyleClass().add("cbscard");

        getChildren().add(cbsListItem);

    }

}
