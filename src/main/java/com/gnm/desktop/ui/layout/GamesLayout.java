package com.gnm.desktop.ui.layout;

import com.gnm.desktop.res.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class GamesLayout extends VBox {

    public GamesLayout() {

        Label test = new Label();
        test.setTextFill(Paint.valueOf(Color.white));
        test.setText("بازی ها");
        test.setAlignment(Pos.BASELINE_CENTER);
        getChildren().add(test);
    }
}