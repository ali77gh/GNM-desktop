package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

import java.util.ArrayList;
import java.util.List;

public class Customer implements Model {

    private String id;

    public String name;
    public String phone;
    public int credit; // اعتبار
    public List<String> games;

    public Customer(String name, String phone,List<String> games) {
        this.name = name;
        this.phone = phone;
        this.credit = 0;
        this.games = games;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public void addGame(String name) {
        games.add(name);
    }

    public void removeGame(String name) {
        games.remove(name);
    }
}
