package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.Main;
import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public abstract class BaseDialog {

    private Stage stage;
    private Pane layout;

    public void setup(Pane layout, Node closeBtn, int width, int height) {

        Stage pop = new Stage();

        layout.setStyle("-fx-background-radius: 10 ");

        closeBtn.setOnMouseClicked(event -> close());

        Scene scene = new Scene(layout, width, height);
        scene.fillProperty().setValue(Paint.valueOf("#ffffff00"));

        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setScene(scene);
        pop.setAlwaysOnTop(true);
        pop.centerOnScreen();
        pop.initStyle(StageStyle.TRANSPARENT);

        stage = pop;
        this.layout = layout;
    }

    public void show() {
        stage.show();
        fadeAnim(layout, true);
        blurMainWindow(true);
    }

    public void close() {
        stage.close();
        fadeAnim(layout, false);
        blurMainWindow(false);
    }

    //for fade out pass false and for fade in pass true
    public void fadeAnim(Pane pane, boolean in) {
        FadeTransition fade = new FadeTransition();


        //setting the duration for the Fade transition
        fade.setDuration(Duration.millis(200));

        //setting the initial and the target opacity value for the transition
        if (in) {
            fade.setFromValue(0);
            fade.setToValue(1);
        } else {
            fade.setFromValue(1);
            fade.setToValue(0);
        }


        //setting Circle as the node onto which the transition will be applied
        fade.setNode(pane);
    }

    public static void blurMainWindow(boolean fade) {

        BoxBlur b = new BoxBlur();

        if (fade) {
            b.setHeight(3);
            b.setWidth(3);
            b.setIterations(1);
        } else {
            b.setHeight(0);
            b.setWidth(0);
            b.setIterations(0);
        }

        Main.root.setEffect(b);
    }
}
