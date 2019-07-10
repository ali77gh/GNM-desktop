package com.gnm.desktop.res.css;

import java.io.File;

public class CSSStyler {

    public static String get(String cssFile){

        File file=new File("src/main/resources/css/"+cssFile);

        String path="file:///" + file.getAbsolutePath().replace("\\", "/");

        return path;
    }

}
