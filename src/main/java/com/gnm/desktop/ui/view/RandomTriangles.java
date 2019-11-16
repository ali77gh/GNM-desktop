package com.gnm.desktop.ui.view;

import com.gnm.desktop.ui.layout.RegisterLayout;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Random;


public class RandomTriangles extends StackPane {

    private Random random = new Random();

    private RandomTriangles(int count, String styleClass, double width, double height) {


        this.setAlignment(Pos.TOP_LEFT);
        for (int i = 0; i <= count; i++) {
            Label triangle = new Label();

            triangle.setStyle(styleClass);
            triangle.getStyleClass().addAll("triangle");
            double size = getRand(20, 100);
            triangle.setPrefSize(size * 2, size);
            fadeInAnim(triangle, getRand(0.2, 1));
            triangle.setRotate(getRand(0, 360));
            StackPane.setMargin(triangle, new Insets(getRand(0, height - size), 0, 0, getRand(0, width - size)));
            // do what ever

            this.getChildren().add(triangle);

        }
    }

    private double getRand(double min, double max) {
        return min + (max - min) * random.nextDouble();
    }

    private void fadeInAnim(Label triangle, double value) {
        triangle.setOpacity(0);

        var fade = new FadeTransition();
        fade.setDuration(Duration.millis(600));
        fade.setFromValue(0);
        fade.setDelay(Duration.millis(getRand(200, 15000)));
        fade.setToValue(value);
        fade.setNode(triangle);
        fade.play();
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
