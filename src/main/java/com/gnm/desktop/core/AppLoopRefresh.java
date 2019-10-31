package com.gnm.desktop.core;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppLoopRefresh {

    private static final int REFRESH_LOOP_TIME = 1000; // in mili seconds

    private static List<AppLoopRefreshCallback> list;

    public static void init() {

        list = new ArrayList<>();

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (var i : list) {
                        i.callback();
                    }
                });
            }
        }, REFRESH_LOOP_TIME, REFRESH_LOOP_TIME);
    }

    public static void register(AppLoopRefreshCallback cb) {
        list.add(cb);
    }

    public interface AppLoopRefreshCallback {
        void callback();
    }
}
