package com.gnm.desktop.ui.layout;

import com.gnm.desktop.res.Color;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class PricesLayout extends VBox {

    public PricesLayout() {

        Label test = new Label();
        test.setTextFill(Paint.valueOf(Color.white));
        test.setText("قیمت ها");
        test.setAlignment(Pos.BASELINE_CENTER);
        getChildren().add(test);
    }
}
