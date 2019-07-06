package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

public class Customer implements Model {

    private String id;

    public String name;
    public String phone;
    public int credit; // اعتبار

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.credit = 0;
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
