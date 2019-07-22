package com.gnm.desktop.core;

import javafx.application.Platform;

public class ThreadHelper {

    public static void sleep(int sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException ignored) {

        }
    }

    public static void delayInUI(int millis, DelayCallback delayCallback) {

        delayOutUI(millis, () -> Platform.runLater(delayCallback::cb));

    }

    public static void delayOutUI(int millis, DelayCallback delayCallback) {

        new Thread(() -> {
            sleep(millis);

            delayCallback.cb();
        }).start();

    }

    public interface DelayCallback {
        void cb();
    }
}
