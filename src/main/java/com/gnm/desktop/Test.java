package com.gnm.desktop;

import com.gnm.desktop.core.Log;
import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.TimeBaseService;

public class Test {

    public static void main(String[] args) {

        CountBaseTest();
        TimeBaseTest();
    }

    private static void CountBaseTest(){
        Log.d("\n-------CountBaseTest-------");
        CountBaseService cbs = new CountBaseService(15000);

        Log.d(cbs.getCurrentCost());
        cbs.increaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.increaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.increaseCount();
        Log.d(cbs.getCurrentCost());
    }

    private static void TimeBaseTest(){
        Log.d("\n-------TimeBaseTest-------");
        TimeBaseService tbs = new TimeBaseService(5000);

        new Thread(() -> {
            while(true){
                Log.d(tbs.getCurrentCost());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.pause();
            Log.d("pause");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.resume();
            Log.d("resume");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.pause();
            Log.d("pause");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.resume();
            Log.d("resume");
        }).start();
    }
}
