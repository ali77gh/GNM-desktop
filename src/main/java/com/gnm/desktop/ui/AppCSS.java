package com.gnm.desktop.ui;

import javafx.scene.layout.Pane;

import java.io.File;

public class AppCSS {

    public static String get(String cssFile){

        File file = new File("src/main/resources/css/" + cssFile);

        return "file:///" + file.getAbsolutePath().replace("\\", "/");
    }

    //style will add to all child s too
    public static void load(Pane pane) {
        pane.getStylesheets().add(AppCSS.get("app.css"));
        pane.getStylesheets().add(AppCSS.get("icon.css"));
        pane.getStylesheets().add(AppCSS.get("shape.css"));
        pane.getStylesheets().add(AppCSS.get("defaults.css"));
        pane.getStylesheets().add(AppCSS.get("appMmd.css"));
    }

}
