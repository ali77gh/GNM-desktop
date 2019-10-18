package com.gnm.desktop.core.calculator;

public class CountBaseService implements Service {

    private String serviceName;
    private int oneItemCost;
    private int count = 1;

    public CountBaseService(String name, int oneItemCost) {
        serviceName = name;
        if (oneItemCost <= 0)
            throw new RuntimeException("cost should be grater than 0 , current:" + oneItemCost);
        this.oneItemCost = oneItemCost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void increaseCount(){
        count++;
    }

    public void decreaseCount(){
        if (count != 1) count--; //count cant be less than 0
    }

    public int getCount() {
        return count;
    }

    public int getOneItemCost() {
        return oneItemCost;
    }

    @Override
    public int getCurrentCost() {
        return oneItemCost * count;
    }
}
