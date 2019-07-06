package com.gnm.desktop.data.model;

import com.gnm.desktop.data.Model;

import java.util.ArrayList;
import java.util.List;

public class PricePerHour implements Model {

    public String id;

    public String name;// (مثلا دو دسته و سه دسته و ...)
    public int pricePerHour;
    public List<PriceHistory> priceHistory;// تاریخچه ی تغییرات قیمتشو بهش نشون بدیم :)

    public PricePerHour(String name, int pricePerHour) {
        this.name = name;
        this.pricePerHour = pricePerHour;
        priceHistory = new ArrayList<>();
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public class PriceHistory { // فیلد های تو در تو هم ساپورت میکنه :)
        public long time; // time stamp
        public int price;
    }
}
