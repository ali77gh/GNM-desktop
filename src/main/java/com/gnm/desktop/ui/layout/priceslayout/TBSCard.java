package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.data.model.PricePerHour;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class TBSCard extends VBox{

    public TBSCard(PricePerHour pph){

        super(8);

        getStylesheets().add(CSSStyler.get("app.css"));

        setPadding(new Insets(10,10,10,10));
        setPrefSize(200, 100);
        setAlignment(Pos.TOP_CENTER);
        getStyleClass().add("tbscard");


        Label lblName=new Label(pph.name);
        lblName.setPrefHeight(20);
        lblName.getStyleClass().add("tbscard_lblname");

        Pane line=new Pane();
        line.getStyleClass().add("tbscard_line");
        line.setPrefWidth(100);
        line.setPrefHeight(2);
        Group seprator=new Group();
        seprator.getChildren().add(line);

        Label lblPrice=new Label(String.valueOf(pph.pricePerHour));
        lblPrice.setPrefHeight(35);
        lblPrice.getStyleClass().add("tbscard_lblprice");


        setOnMouseClicked(event -> {

                new PopupEditService(pph);
                PricesLayout.makeTimeBaseServiceCards();

        });

        getChildren().addAll(lblName,seprator,lblPrice);
    }

}
