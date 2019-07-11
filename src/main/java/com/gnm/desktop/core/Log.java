package com.gnm.desktop.core;

import com.google.gson.Gson;

import java.util.List;

public class Log {

    public static void d(Object o){
        print(new Gson().toJson(o));
    }

    public static void d(String tag, Object o){
        print(tag + " : " + new Gson().toJson(o));
    }

    public static void d(String tag, List list) {

        print("-------<" + tag + ">-------");
        for (Object o : list) {
            print("\t" + new Gson().toJson(o));
        }
        print("-------</" + tag + ">-------");
    }

    public static void d(List list) {

        print("-------<list>-------");
        for (Object o : list) {
            print("\t" + new Gson().toJson(o));
        }
        print("-------</list>-------");
    }

    private static void print(String string) {
        System.out.println(string);
    }
}
