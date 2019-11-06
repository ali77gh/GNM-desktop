package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.data.model.CountBaseAutoComplete;
import com.gnm.desktop.ui.dialog.EditCBServiceDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

class CBSListItem extends AnchorPane {

    private final int height=40;

    CBSListItem(int number, CountBaseAutoComplete cbs, Boolean isColored) {

        setPrefHeight(height);
        setOnMouseClicked(event -> new EditCBServiceDialog(cbs));

        Label lblNum = new Label(String.valueOf(number));
        lblNum.getStyleClass().add("cbsListItem_lblNum");
        lblNum.setAlignment(Pos.BASELINE_CENTER);
        //lblNum.setPrefWidth(10);
        AnchorPane.setTopAnchor(lblNum, 0.0);
        AnchorPane.setRightAnchor(lblNum, 5.0);
        AnchorPane.setBottomAnchor(lblNum, 0.0);


        Label lblName = new Label(cbs.name);
        lblName.getStyleClass().add("cbsListItem_lblName");
        lblName.setAlignment(Pos.BASELINE_RIGHT);
        AnchorPane.setTopAnchor(lblName, 0.0);
        AnchorPane.setRightAnchor(lblName, 25.0);
        AnchorPane.setBottomAnchor(lblName, 0.0);

        Label lblPrice = new Label(cbs.price + "T");
        lblPrice.getStyleClass().add("cbsListItem_lblPrice");
        lblPrice.setAlignment(Pos.BASELINE_LEFT);
        lblPrice.setPadding(new Insets(0,0,0,10));
        AnchorPane.setTopAnchor(lblPrice, 0.0);
        AnchorPane.setLeftAnchor(lblPrice, 0.0);
        AnchorPane.setBottomAnchor(lblPrice, 0.0);

        if(isColored)
            getStyleClass().add("cbsListItemColored");
            
        getChildren().addAll(lblPrice,lblName,lblNum);

    }
}
