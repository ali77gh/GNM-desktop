package com.gnm.desktop.ui.view;

import com.gnm.desktop.ui.layout.RegisterLayout;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.util.Random;


public class RandomTriangles extends StackPane {

    private Random random = new Random();

    public RandomTriangles(int count, String styleClass, double width, double heigh) {


        this.setAlignment(Pos.TOP_LEFT);
        for (int i = 0; i <= count; i++) {
            Label triangle = new Label();

            triangle.setStyle(styleClass);
            triangle.getStyleClass().addAll("triangle");
            double size = getRand(20, 100);
            triangle.setPrefSize(size * 2, size);
            triangle.setOpacity(getRand(0.2, 1));
            triangle.setRotate(getRand(0, 360));
            StackPane.setMargin(triangle, new Insets(getRand(0, heigh - size), 0, 0, getRand(0, width - size)));
            // do what ever

            this.getChildren().add(triangle);

        }
    }

    private double getRand(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    public static RandomTriangles getForRegister() {
        return new RandomTriangles(
                30,
                "-fx-background-color: fx_lightGrey;",
                RegisterLayout.width / 2,
                RegisterLayout.height
        );
    }

    public static RandomTriangles getForHome() {
        return new RandomTriangles(
                30,
                "-fx-background-color: #2b2b32;",
                600,
                1100
        );
    }


}
