package com.gnm.desktop.core;

import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class AppRefresh {

    private static final int REFRESH_LOOP_TIME = 60000; // in milli seconds

    private static List<AppRefreshCallback> list;

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

    public static void registerInLoop(AppRefreshCallback cb) {
        list.add(cb);
    }

    public interface AppRefreshCallback {
        void callback();
    }


    // request to refresh other layouts statically

    private static AppRefreshCallback[] layoutsCallBacks = new AppRefreshCallback[5];

    public static void registerLayout(int layoutIndex, AppRefreshCallback cb) {
        layoutsCallBacks[layoutIndex] = cb;
    }

    // 😂
    public static void pleaseRefresh(int layoutIndex) {
        layoutsCallBacks[layoutIndex].callback();
    }
}
