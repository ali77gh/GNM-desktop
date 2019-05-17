package com.gnm.desktop.core;

public class Log {

    public static void d(Object o){
        System.out.println(o);
    }

    public static void d(String tag, Object o){
        d(tag + " : " + o);
    }
}
