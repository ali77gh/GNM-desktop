package com.gnm.desktop.core.calculator;

public class PaymentBaseService implements Service {

    private int cost;

    public PaymentBaseService(int cost) {
        if (cost <= 0)
            throw new RuntimeException("cost should be positive");
        this.cost = -1 * cost;
    }

    @Override
    public int getCurrentCost() {
        return cost;
    }
}
