package com.gnm.desktop.core;

public class Sleep {

    public static void sleep(int sleep){
        try {
            Thread.sleep(sleep);
        }catch (InterruptedException e){

        }
    }
}
