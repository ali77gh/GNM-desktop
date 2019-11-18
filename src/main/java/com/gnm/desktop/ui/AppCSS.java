package com.gnm.desktop.ui;

import javafx.scene.layout.Pane;

import java.io.File;

public class AppCSS {

    public static String get(String cssFile){

        File file = new File("src/main/resources/css/" + cssFile);

        return "file:///" + file.getAbsolutePath().replace("\\", "/");
    }

    public static String getExp(String cssFile) {

        File file = new File(System.getProperty("user.dir") + "/css/" + cssFile);

        return "file:///" + file.getAbsolutePath().replace("\\", "/");
    }

    //style will add to all child s too
    public static void load(Pane pane) {

        //local (todo remove these lines on final export)
        // this will error in jar mode
        pane.getStylesheets().add(AppCSS.getExp("colors.css").replace(" ", "%20"));
        pane.getStylesheets().add(AppCSS.getExp("app.css").replace(" ", "%20"));
        pane.getStylesheets().add(AppCSS.getExp("icon.css").replace(" ", "%20"));
        pane.getStylesheets().add(AppCSS.getExp("shape.css").replace(" ", "%20"));
        pane.getStylesheets().add(AppCSS.getExp("defaults.css").replace(" ", "%20"));

        //export
        // this will error on gradle mode
        pane.getStylesheets().add(AppCSS.get("colors.css"));
        pane.getStylesheets().add(AppCSS.get("app.css"));
        pane.getStylesheets().add(AppCSS.get("icon.css"));
        pane.getStylesheets().add(AppCSS.get("shape.css"));
        pane.getStylesheets().add(AppCSS.get("defaults.css"));
    }

}
