package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.DBPager;
import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.SellLog;

import java.util.ArrayList;
import java.util.List;


public class SellLogRepo extends GenericDAO<SellLog> {

    public SellLogRepo() {
        super(SellLog.class, true);
    }

    //customer bass queries

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

    //service base queries

    public List<SellLog> getWithServiceName(String serviceName) {
        return getWithCondition(object -> ((SellLog) object).serviceName.equals(serviceName));
    }

    public List<String> getAllServiceNames() {

        List<String> names = new ArrayList<>();

        getWithCondition(object -> {
            String name = ((SellLog) object).serviceName;
            if (names.indexOf(name) == -1) {
                names.add(name);
            }
            return false;
        });

        return names;
    }

    /**
     * چقدر از فروختن یه سرویس پول درآوردیم
     */
    public int getHowMatchServiceIncome(String serviceName) {
        List<SellLog> sellLogs = getWithServiceName(serviceName);

        int sum = 0;
        for (SellLog sellLog : sellLogs) {
            sum += sellLog.income;
        }
        return sum;
    }

    /**
     * چند ثانیه تا حالا این سرویس توسط مشتری ها استفاده شده
     */
    public int getHowMatchServicePlayed(String serviceName) {
        List<SellLog> sellLogs = getWithServiceName(serviceName);

        int sum = 0;
        for (SellLog sellLog : sellLogs) {
            sum += sellLog.serviceTime;
        }
        return sum;
    }

    //todo add filter by time (unix time)
}
