package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

public class PricePerHour implements Model {

    public String id;

    public String name;// (مثلا دو دسته و سه دسته و ...)
    public int pricePerHour;

    public PricePerHour(String name, int pricePerHour) {
        this.name = name;
        this.pricePerHour = pricePerHour;
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
