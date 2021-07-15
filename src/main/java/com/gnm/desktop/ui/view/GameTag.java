package com.gnm.desktop.ui.view;

import javafx.scene.control.Label;

public class GameTag extends Label {




    public GameTag(String name){
        setText(name);
        getStyleClass().add("customerCard_lblgameTags");
    }




    public GameTag(String name,Boolean deletable,deletableGameTag delete){
        setText(name);

        if (deletable){
            getStyleClass().add("customerCard_lblgameTagsDeletable");
            setOnMouseClicked(event -> {

                delete.OnDelete(getText());

            });

        }else {
            getStyleClass().add("customerCard_lblgameTags");
        }
    }

    public interface deletableGameTag{
        void OnDelete(String tagName);
    }

}
