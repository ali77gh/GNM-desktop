package com.gnm.desktop.res.css;

import javafx.scene.layout.Pane;

import java.io.File;

public class CSSStyler {

    public static String get(String cssFile){

        File file = new File("src/main/resources/css/" + cssFile);

        return "file:///" + file.getAbsolutePath().replace("\\", "/");
    }

    public static void loadStyles(Pane pane) {
        pane.getStylesheets().add(CSSStyler.get("app.css"));
        pane.getStylesheets().add(CSSStyler.get("icon.css"));
        pane.getStylesheets().add(CSSStyler.get("shape.css"));
        pane.getStylesheets().add(CSSStyler.get("defaults.css"));
        pane.getStylesheets().add(CSSStyler.get("appMmd.css"));
    }

}
