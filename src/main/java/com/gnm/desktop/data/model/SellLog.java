package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

public class SellLog implements Model {

    public String id;

    public String customerId; //set "guest" for guests
    public long time; //time stamp -> new Date().getTime() (تاریخ هم تو دل این هست)

    public int income; // چقد پول داده
    public String serviceName; // "آب" , "دو دسته" , "چیپس" , ...
    public int serviceTime; // چقد ساعت بازی کرده. واسه بوفه صفر میزاریم

    public SellLog(String customerId, long time, int income, String serviceName, int serviceTime) {
        this.customerId = customerId;
        this.time = time;
        this.income = income;
        this.serviceName = serviceName;
        this.serviceTime = serviceTime;
    }

    public SellLog(String customerId, long time, int income, String serviceName) {
        this.customerId = customerId;
        this.time = time;
        this.income = income;
        this.serviceName = serviceName;
        serviceTime = -1;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}
