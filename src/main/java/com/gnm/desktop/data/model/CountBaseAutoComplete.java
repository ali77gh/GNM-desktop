package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

public class CountBaseAutoComplete implements Model {

    public String id;

    public String name;
    public int price;

    public CountBaseAutoComplete(String name, int price) {
        this.name = name;
        this.price = price;
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
