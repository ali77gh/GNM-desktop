package com.gnm.desktop.core.dateTime;

/**
 * Created by ali on 9/11/18.
 */

public class UnixTimeTools {

    public static int Now() {
        return (int) (System.currentTimeMillis() / 1000L); //toSec
    }
}
