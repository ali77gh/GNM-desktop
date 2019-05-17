package com.gnm.desktop.core.calculator;

import java.util.ArrayList;
import java.util.List;

public class ActiveCustomer {

    private final String GUEST = "guest";

    private String customerId;
    private String customerName;
    private List<Service> services;

    public ActiveCustomer(String customerId , String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
        this.services = new ArrayList<>();
    }

    //guest user
    public ActiveCustomer(){
        customerId = GUEST;
        this.customerName = "";
        this.services = new ArrayList<>();
    }


    public boolean isGuest(){
        return customerId.equals(GUEST);
    }

    public String getCustomerId() {
        return customerId;
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
}
