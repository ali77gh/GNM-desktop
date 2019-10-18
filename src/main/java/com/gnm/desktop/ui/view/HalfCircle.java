package com.gnm.desktop.ui.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class HalfCircle extends StackPane {


    private HalfCircle() {
        //no one can make instance from outside ;)
    }

    public static HalfCircle getAddNewServiceCircle() {

        HalfCircle root = new HalfCircle();
        root.setPrefSize(60, 30);
        root.setAlignment(Pos.CENTER);

        Label circle = new Label();
        circle.setPrefSize(60, 30);
        circle.getStyleClass().add("half_circle_bottom");

        Label plus = new Label();
        plus.setPrefSize(16, 16);
        plus.getStyleClass().add("plus");
        plus.setStyle("-fx-background-color : fx_lightBlack;");

        root.getChildren().add(circle);
        root.getChildren().add(plus);
        return root;
    }

    public static HalfCircle getRemoveNewServiceCircle() {

        HalfCircle root = new HalfCircle();
        root.setPrefSize(60, 30);
        root.setAlignment(Pos.CENTER);

        Label circle = new Label();
        circle.setPrefSize(60, 30);
        circle.getStyleClass().add("half_circle_bottom");

        Label plus = new Label();
        plus.setPrefSize(16, 16);
        plus.getStyleClass().add("plus");
        plus.setStyle("-fx-background-color : fx_lightBlack;");

        root.getChildren().add(circle);
        root.getChildren().add(plus);
        return root;
    }
}
