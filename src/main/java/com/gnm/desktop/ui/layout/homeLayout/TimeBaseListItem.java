package com.gnm.desktop.ui.layout.homeLayout;

import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.ui.dialog.AreYouSureDialog;
import com.gnm.desktop.ui.view.HalfCircle;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TimeBaseListItem extends AnchorPane {

    private ServiceCardItemCallback callback;

    TimeBaseListItem(TimeBaseService timeBaseService) {

        //remove service btn
        HalfCircle closeBtn = HalfCircle.getRemoveNewServiceCircle();
        closeBtn.setOnMouseClicked(mouseEvent -> {
            new AreYouSureDialog(() -> {
                callback.onDelete();
            });
        });
        AnchorPane.setRightAnchor(closeBtn, 0.0);

        //service name
        Label name = new Label(timeBaseService.getServiceName() + " (" + timeBaseService.getConsoleName() + ")");
        name.getStyleClass().add("dialogText");
        AnchorPane.setRightAnchor(name, 40.0);
        AnchorPane.setTopAnchor(name, 10.0);

        //timer
        Label time = new Label(getTimeString(timeBaseService.getActiveTimeInHours()));
        time.getStyleClass().add("flatButton");
        AnchorPane.setRightAnchor(time, 132.0);
        AnchorPane.setTopAnchor(time, 10.0);

        //pause and play btn
        Label pauseAndPlay = new Label();
        pauseAndPlay.setPrefSize(16, 16);
        if (timeBaseService.isPaused()) {
            pauseAndPlay.getStyleClass().add("play");
        } else {
            pauseAndPlay.getStyleClass().add("pause");
        }
        AnchorPane.setRightAnchor(pauseAndPlay, 190.0);
        AnchorPane.setTopAnchor(pauseAndPlay, 10.0);
        pauseAndPlay.setStyle("-fx-background-color : fx_primary;");
        pauseAndPlay.setOnMouseClicked(mouseEvent -> {

            if (timeBaseService.isPaused())
                timeBaseService.resume();
            else
                timeBaseService.pause();

            callback.refreshRequest();
        });

        // cost
        Label paymentValue = new Label(timeBaseService.getCurrentCost() + "T");
        paymentValue.getStyleClass().add("dialogText");
        AnchorPane.setLeftAnchor(paymentValue, 46.0);
        AnchorPane.setTopAnchor(paymentValue, 10.0);


        this.getChildren().addAll(
                closeBtn,
                name,
                time,
                pauseAndPlay,
                paymentValue
        );
    }

    public static String getTimeString(float hour) {

        float min = hour % 1;
        hour -= min; // round down
        min *= 60;

        return forceTwoSize(hour) + ":" + forceTwoSize(min);
    }

    private static String forceTwoSize(float num) {
        if (num < 10)
            return "0" + (int) num;
        else
            return String.valueOf((int) num);
    }

    public void setCallback(ServiceCardItemCallback callback) {
        this.callback = callback;
    }
}
