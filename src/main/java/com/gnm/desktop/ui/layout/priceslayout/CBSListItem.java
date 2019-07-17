package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class CBSListItem extends AnchorPane {
    public CBSListItem(){

        getStylesheets().add(CSSStyler.get("app.css"));
        setPrefHeight(30);

        Label lblNum=new Label("1");
        lblNum.getStyleClass().add("cbsListItem_lblNum");
        lblNum.setAlignment(Pos.BASELINE_CENTER);
        lblNum.setPrefWidth(40);
        AnchorPane.setTopAnchor(lblNum, 0.0);
        AnchorPane.setRightAnchor(lblNum, 0.0);
        AnchorPane.setBottomAnchor(lblNum, 0.0);

        Label lblName=new Label("چیپس");
        lblName.getStyleClass().add("cbsListItem_lblName");
        lblName.setAlignment(Pos.BASELINE_RIGHT);
        AnchorPane.setTopAnchor(lblName, 0.0);
        AnchorPane.setRightAnchor(lblName, 40.0);
        AnchorPane.setBottomAnchor(lblName, 0.0);

        Label lblPrice=new Label("5000");
        lblPrice.getStyleClass().add("cbsListItem_lblPrice");
        lblPrice.setAlignment(Pos.BASELINE_LEFT);
        lblPrice.setPadding(new Insets(0,0,0,10));
        AnchorPane.setTopAnchor(lblPrice, 0.0);
        AnchorPane.setLeftAnchor(lblPrice, 0.0);
        AnchorPane.setBottomAnchor(lblPrice, 0.0);


        getChildren().addAll(lblPrice,lblName,lblNum);

    }
}
