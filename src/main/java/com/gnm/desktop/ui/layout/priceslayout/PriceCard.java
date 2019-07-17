package com.gnm.desktop.ui.layout.priceslayout;

import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.model.PricePerHour;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;

public class PriceCard extends VBox{

    public PriceCard(PricePerHour pph){

        super(5);
        setPadding(new Insets(10,10,10,10));
        setPrefSize(150, 100);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #fff;"+
                "-fx-background-radius: 20 20 20 20");

        Label lbl1=new Label(pph.name);
        lbl1.setTextFill(Paint.valueOf("#353535"));
        lbl1.setPrefHeight(20);
        lbl1.setStyle("-fx-font-size: 18;");

        Line l1=new Line(0,0,100,0);
        Line l2=new Line(0,1,100,1);
        Group seprator=new Group();
        seprator.getChildren().addAll(l1,l2);

        Label lbl2=new Label(String.valueOf(pph.pricePerHour));
        lbl2.setTextFill(Paint.valueOf("#353535"));
        lbl2.setPrefHeight(50);
        lbl2.setStyle("-fx-font-size:20;"+
                "-fx-font-weight:bold;");


        setOnMouseClicked(event -> {

                new PopupEditService(pph);
                PricesLayout.makeTimeBaseServiceCards();

        });

        getChildren().addAll(lbl1,seprator,lbl2);
    }

}
