package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

public class TestModel implements Model {

    public String name;
    public String Lname;
    private String id;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }
}