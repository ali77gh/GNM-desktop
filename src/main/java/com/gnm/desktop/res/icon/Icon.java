package com.gnm.desktop.res.icon;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Icon {

    public static Image get(String name){

        try {
            return new Image(new FileInputStream("src/main/resources/icons/" + name));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
