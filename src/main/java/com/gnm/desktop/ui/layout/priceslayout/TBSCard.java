package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.ui.dialog.EditTBServiceDialog;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class TBSCard extends VBox {

    TBSCard(PricePerHour pph) {

        super(8);

        setPadding(new Insets(10,10,10,10));
        setPrefSize(180, 100);
        setAlignment(Pos.TOP_CENTER);
        getStyleClass().add("card");


        Label lblName=new Label(pph.name);
        lblName.setPrefHeight(20);
        lblName.getStyleClass().addAll("size18");

        Pane line = new Pane();
        line.getStyleClass().add("tbsCard_line");
        line.setPrefWidth(150);
        line.setPrefHeight(2);
        Group seprator=new Group();
        seprator.getChildren().add(line);

        Label lblPrice = new Label(pph.pricePerHour + "T");
        lblPrice.setPrefHeight(35);
        lblPrice.getStyleClass().addAll("size20");


        setOnMouseClicked(event -> new EditTBServiceDialog(pph));

        getChildren().addAll(lblName,seprator,lblPrice);
    }

}
