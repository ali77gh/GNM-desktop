package com.gnm.desktop.data.model;

import com.gnm.desktop.core.calculator.Service;
import com.gnm.desktop.data.Model;

import java.util.ArrayList;
import java.util.List;

public class ActiveCustomer implements Model {

    private static final String GUEST = "guest";

    private String customerId;
    private String customerName;
    private List<Service> services;

    public ActiveCustomer(String customerId , String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
        this.services = new ArrayList<>();
    }

    //guest user
    public ActiveCustomer(String customerName){
        customerId = GUEST;
        this.customerName = customerName;
        this.services = new ArrayList<>();
    }

    //guest user
    public ActiveCustomer(){
        customerId = GUEST;
        this.customerName = "مهمان";
        this.services = new ArrayList<>();
    }


    public boolean isGuest(){
        return customerId.equals(GUEST);
    }

    public String getCustomerName() {
        return customerName;
    }

    public Service getService(int index) {
        return services.get(index);
    }

    public int getServicesSize() {
        return services.size();
    }

    public int getCostSum(){
        int sum = 0;
        for (Service service : services){
            sum += service.getCurrentCost();
        }
        return sum;
    }

    @Override
    public String getId() {
        return customerId;
    }

    @Override
    public void setId(String id) {
        this.customerId = id;
    }
}
