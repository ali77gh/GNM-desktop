package com.gnm.desktop.ui.layout.mainpanel;

import com.gnm.desktop.res.css.CSSStyler;
import javafx.scene.layout.AnchorPane;

public class Toolbar extends AnchorPane {


    public Toolbar(){

        setPrefHeight(40);

        //load css file with cssstyler class
        getStylesheets().add(CSSStyler.get("BackgroundColor.css"));

        getStyleClass().add("black");

    }

}
