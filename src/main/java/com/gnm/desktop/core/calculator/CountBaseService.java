package com.gnm.desktop.core.calculator;

public class CountBaseService implements Service {

    private String serviceName;
    private int oneItemCost;
    private int count = 0;

    public CountBaseService(int oneItemCost){
        if (oneItemCost <= 0)
            throw new RuntimeException("cost should be grater than 0 , current:" + oneItemCost);
        this.oneItemCost = oneItemCost;
    }

    public void increaseCount(){
        count++;
    }

    public void decreaseCount(){
        if (count != 0) count--; //count cant be less than 0
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
