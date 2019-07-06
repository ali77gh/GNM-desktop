package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.SellLog;

import java.util.List;


public class SellLogRepo extends GenericDAO<SellLog> {

    public SellLogRepo() {
        super(SellLog.class, true);
    }

    public List<SellLog> getWithCustomerId(String customerId) {
        return getWithCondition(object -> ((SellLog) object).customerId.equals(customerId));
    }

    /**
     * این مشتری تا الان چقد پول داده به این گیم نت
     */
    public int getHowMatchCustomerPay(String customerId) {
        List<SellLog> sellLogs = getWithCustomerId(customerId);

        int sum = 0;
        for (SellLog sellLog : sellLogs) {
            sum += sellLog.income;
        }
        return sum;
    }

    /**
     * این مشتری تا الان چند ساعت تو این گیم نت بازی کرده
     */
    public int getHowMatchCustomerPlay(String customerId) {
        List<SellLog> sellLogs = getWithCustomerId(customerId);
        int sum = 0;
        for (SellLog sellLog : sellLogs) {
            sum += sellLog.serviceTime;
        }
        return sum;
    }


    //اینجا کلی چیز میتونی بنویسی ممد
    //انواع آمار های چرتو پرت
    //مخصوصا بعد از اینکه سیستم تاریخ رو اضافه کنم
}
