package com.gnm.desktop.core.calculator;

import java.util.Date;

public class TimeBaseService implements Service {

    private String serviceName;
    private int oneHourCost;
    private long startTime; //unixTime
    private long pauseSum; //unixTime
    private long lastPauseTime = 0;
    private String consoleName; // معمولا عدد

    public TimeBaseService(String name, int oneHourCost, String consoleName) {
        serviceName = name;
        this.consoleName = consoleName;
        if (oneHourCost <= 0)
            throw new RuntimeException("cost should be grater than 0 , current:" + oneHourCost);
        this.oneHourCost = oneHourCost;
        startTime = now();
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getConsoleName() {
        return consoleName;
    }

    public void pause(){
        if (lastPauseTime != 0)
            throw new RuntimeException("already paused");

        lastPauseTime = now();
    }

    public void resume(){
        if (lastPauseTime == 0)
            throw new RuntimeException("already resumed");

        long diff = now() - lastPauseTime;
        pauseSum += diff;
        lastPauseTime = 0;
    }

    public boolean isPaused() {
        return lastPauseTime != 0;
    }

    public int getOneHourCost() {
        return oneHourCost;
    }

    public long getStartTime() {
        return startTime;
    }

    public float getActiveTimeInHours(){
        float diff = now() - startTime;
        diff -= pauseSum;
        if (lastPauseTime != 0)
            diff -= now() - lastPauseTime;

        return  diff / 3600000; //convert to milli second to hour
    }

    @Override
    public int getCurrentCost() {
        return (int) (getActiveTimeInHours() * oneHourCost);
    }

    private long now(){
        return new Date().getTime();
    }

}
