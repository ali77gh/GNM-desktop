package com.gnm.desktop.ui.dialog;

import com.gnm.desktop.Main;
import com.gnm.desktop.ui.AppCSS;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

abstract class BaseDialog {

    private static final int TITLE_HEIGHT = 80;

    private Stage stage;
    private Pane layout;

    void setup(Pane layout, Node closeBtn, String titleStr, int width, int height) {

        closeBtn.setOnMouseClicked(event -> close());

        //layout and title
        layout.setPadding(new Insets(20));
        var layoutWithTitle = new VBox();
        var title = new Label();
        title.setText(titleStr);
        title.getStyleClass().addAll("dialogText");
        title.setAlignment(Pos.CENTER);
        title.setPrefWidth(width);
        title.setPrefHeight(TITLE_HEIGHT);
        layoutWithTitle.getChildren().addAll(title, layout);
        layoutWithTitle.getStyleClass().add("dialog");

        AppCSS.load(layoutWithTitle);

        //scene
        Scene scene = new Scene(layoutWithTitle, width, height + TITLE_HEIGHT);
        scene.fillProperty().setValue(Paint.valueOf("#ffffff00"));

        //stage
        Stage pop = new Stage();
        pop.initModality(Modality.APPLICATION_MODAL);
        pop.setScene(scene);
        pop.setAlwaysOnTop(true);
        pop.centerOnScreen();
        pop.initOwner(Main.getStage());
        pop.initStyle(StageStyle.TRANSPARENT);

        stage = pop;
        this.layout = layoutWithTitle;
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
    private void fadeAnim(Pane pane, boolean in) {
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

    private static void blurMainWindow(boolean fade) {

        BoxBlur b = new BoxBlur();

        if (fade) {
            b.setHeight(5);
            b.setWidth(5);
            //b.setIterations(1);
        } else {
            b.setHeight(0);
            b.setWidth(0);
            b.setIterations(0);
        }

        Main.root.setEffect(b);
    }
}
