package com.gnm.desktop.ui.layout.mainpanel;

import com.gnm.desktop.core.dateTime.JalaliDateTime;
import com.gnm.desktop.res.Color;
import com.gnm.desktop.res.css.CSSStyler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class Toolbar extends AnchorPane {

    public Toolbar(){

        setPrefHeight(40);

        //load css file with cssstyler class
        getStylesheets().add(CSSStyler.get("BackgroundColor.css"));

        Label lbl=new Label();
        lbl.getStyleClass().add("grey");
        lbl.setPrefWidth(240);
        AnchorPane.setTopAnchor(lbl,0.0);
        AnchorPane.setRightAnchor(lbl,0.0);
        AnchorPane.setBottomAnchor(lbl,0.0);

        lbl.setTextFill(Paint.valueOf(Color.white));
        lbl.setAlignment(Pos.CENTER);
        lbl.setStyle("-fx-font-size: 18");


        //second added just for debuging todo remove second in release
        Timeline timeline= new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            JalaliDateTime jalaliDateTime=JalaliDateTime.Now();
            String time=jalaliDateTime.getTimePersianString(true);
            lbl.setText(time);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        getChildren().addAll(lbl);
        getStyleClass().add("black");

    }

}
