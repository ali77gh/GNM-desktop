package com.gnm.desktop.ui.layout;

import com.gnm.desktop.core.ThreadHelper;
import com.gnm.desktop.ui.view.Progressbar;
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class PreLoader extends VBox {

    private static final int MODULES_COUNT = 6;
    private static int readyModules = 0;
    private static Label label;
    private static VBox self;
    private static Progressbar progressbar;

    public PreLoader() {
        super(20);
        self = this;

        getStyleClass().add("preLoader");

        //label
        label = new Label("0%");
        label.getStyleClass().add("tbsCard_lblName");

        progressbar = new Progressbar(300, 4);

        setAlignment(Pos.CENTER);
        getChildren().addAll(label, progressbar);
    }

    public static void ImReady() {

        // loading
        readyModules++;

        float percent = (float) readyModules / MODULES_COUNT;

        label.setText((int) (percent * 100) + "%");
        progressbar.setVal((int) (percent * 100));

        // load completed
        if (readyModules == MODULES_COUNT) {

            int animDuration = 500;

            var fade = new FadeTransition();

            fade.setFromValue(1);
            fade.setToValue(0);
            fade.setDuration(Duration.millis(animDuration));
            fade.setNode(self);

            fade.play();

            ThreadHelper.delayInUI(animDuration + 10, () -> self.setVisible(false));
        }
    }
}
