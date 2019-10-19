package com.gnm.desktop.data.model;

import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.PaymentBaseService;
import com.gnm.desktop.core.calculator.Service;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.data.Model;

import java.util.ArrayList;
import java.util.List;

public class ActiveCustomer implements Model {

    private static final String GUEST = "guest";

    private String customerId;
    private String customerName;

    //چرا اینا رو یه لیست از نوع Service نگرفتم؟
    // چون موقع خوندن از دیتابیس اون Gson نمیتونه پارس کنه
    private List<TimeBaseService> timeBaseServices = new ArrayList<>();
    private List<CountBaseService> countBaseServices = new ArrayList<>();
    private List<PaymentBaseService> paymentBaseServices = new ArrayList<>();


    public ActiveCustomer(String customerId , String customerName){
        this.customerId = customerId;
        this.customerName = customerName;
    }

    //guest user
    public ActiveCustomer(String customerName){
        customerId = GUEST;
        this.customerName = customerName;
    }

    //guest user
    public ActiveCustomer(){
        customerId = GUEST;
        this.customerName = "مهمان";
    }


    public boolean isGuest(){
        return customerId.equals(GUEST);
    }

    public String getCustomerName() {
        return customerName;
    }

    // adding and removeing items from this list will not effect object
    public List<Service> getServicesForLoop() {

        List<Service> temp = new ArrayList<>();
        temp.addAll(timeBaseServices);
        temp.addAll(countBaseServices);
        temp.addAll(paymentBaseServices);
        return temp;
    }

    public int getServiceSize() {
        return getServicesForLoop().size();
    }

    public void addService(Service service) {

        if (service instanceof TimeBaseService) {
            timeBaseServices.add((TimeBaseService) service);
        } else if (service instanceof CountBaseService) {
            countBaseServices.add((CountBaseService) service);
        } else if (service instanceof PaymentBaseService) {
            paymentBaseServices.add((PaymentBaseService) service);
        } else
            throw new RuntimeException("invalid service");
    }

    public void removeService(Service service) {
        if (service instanceof TimeBaseService) {
            timeBaseServices.remove(service);
        } else if (service instanceof CountBaseService) {
            countBaseServices.remove(service);
        } else if (service instanceof PaymentBaseService) {
            paymentBaseServices.remove(service);
        } else
            throw new RuntimeException("invalid service");
    }

    public int getCostSum(){
        int sum = 0;
        for (Service service : getServicesForLoop()) {
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
