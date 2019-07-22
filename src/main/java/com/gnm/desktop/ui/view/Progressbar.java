package com.gnm.desktop.ui.view;

import com.gnm.desktop.res.css.CSSStyler;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class Progressbar extends HBox {

    private Pane progress;
    private int width;

    public Progressbar(int width, int height) {

        this.width = width;

        getStylesheets().add(CSSStyler.get("app.css"));
        getStyleClass().add("progressbar_background");

        progress = new Pane();
        progress.getStyleClass().add("progressbar_self");

        setPrefSize(width, height);
        setMaxSize(width, height);
        progress.setPrefSize(width, height);
        progress.setMaxSize(width, height);

        getChildren().add(progress);
    }

    public void setVal(int prsent) {
        progress.setPrefWidth(prsent * (width / 100));
    }
}
