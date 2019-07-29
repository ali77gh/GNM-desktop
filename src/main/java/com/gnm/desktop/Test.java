package com.gnm.desktop;

import com.gnm.desktop.core.Log;
import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.core.dateTime.JalaliDateTime;
import com.gnm.desktop.core.dateTime.UnixTimeTools;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.DBPager;
import com.gnm.desktop.data.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Test {

    public static void main(String[] args) {


       //DataBaseTest.SellLog();
       DataBaseTest.PagerTest();

    }


    public static class DataBaseTest {

        public static void ActiveCustomer() {
            //remove all
            DB.Init();
            DB.ActiveCustomers.Drop();
            DB.Init();

            //insert
            DB.ActiveCustomers.Insert(new ActiveCustomer(UUID.randomUUID().toString(), "ali"));
            DB.ActiveCustomers.Insert(new ActiveCustomer(UUID.randomUUID().toString(), "mohammad"));
            DB.ActiveCustomers.Insert(new ActiveCustomer("guest", "ali"));

            //delete
            Log.d("all", DB.ActiveCustomers.getAll());
            DB.ActiveCustomers.Remove(DB.ActiveCustomers.getAll().get(1).getId());
            Log.d("all", DB.ActiveCustomers.getAll());
        }

        public static void CountBaseAutoCompelete() {
            //remove all
            DB.Init();
            DB.CountBaseAutoComplete.Drop();
            DB.Init();

            //insert
            DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete("آب معدنی", 2000));
            DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete("نوشابه قوطی", 2000));
            DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete("چیپس فلفلی", 2000));
            DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete("چیپس ساده", 2000));
            DB.CountBaseAutoComplete.Insert(new CountBaseAutoComplete("چیپس پیاز و جعفری", 2000));

            //delete
            Log.d("all", DB.CountBaseAutoComplete.getAll());
            DB.CountBaseAutoComplete.Remove(DB.CountBaseAutoComplete.getAll().get(1).getId());
            Log.d("all", DB.CountBaseAutoComplete.getAll());

            //search
            Log.d("search", DB.CountBaseAutoComplete.getWithPrefix("چیپس", 1));
        }

        public static void CustomerRepo() {
            //remove all
            DB.Init();
            DB.Customers.Drop();
            DB.Init();



            //insert
            DB.Customers.Insert(new Customer("ali", "09387457114  a"));
            DB.Customers.Insert(new Customer("mohammad", "09387457115  m"));
            DB.Customers.Insert(new Customer("jafar", "09387457115  j"));

            //delete
            Log.d("all", DB.Customers.getAll());
            DB.Customers.Remove(DB.Customers.getAll().get(1).getId());
            Log.d("all", DB.Customers.getAll());

            //search
            Log.d("search", DB.Customers.getCustomerByPhoneContains("114"));

            //credit
            Log.d("all", DB.Customers.getAll());
            DB.Customers.IncreaseCredit(DB.Customers.getAll().get(0).getId(), 4000);
            Log.d("all", DB.Customers.getAll());
            DB.Customers.DecreaseCredit(DB.Customers.getAll().get(0).getId(), 6000);
            Log.d("all", DB.Customers.getAll());

        }

        public static void Prices() {
            //remove all
            DB.Init();
            DB.Prices.Drop();
            DB.Init();

            //insert
            DB.Prices.Insert(new PricePerHour("یه دسته", 5000));
            DB.Prices.Insert(new PricePerHour("دو دسته", 6000));
            DB.Prices.Insert(new PricePerHour("سه دسته", 7000));

            //delete
            Log.d("all", DB.Prices.getAll());
            DB.Prices.Remove(DB.Prices.getAll().get(1).id);
            Log.d("all", DB.Prices.getAll());
        }

        public static void SellLog() {

            //remove all
            DB.Init();
            DB.SellLogs.Drop();
            DB.Init();

            //insert
            DB.SellLogs.Insert(new SellLog("guest", UnixTimeTools.Now(), 5000, "یه دسته", 216000));
            DB.SellLogs.Insert(new SellLog("guest", UnixTimeTools.Now(), 5000, "یه دسته", 216000));
            DB.SellLogs.Insert(new SellLog("d", UnixTimeTools.Now(), 5000, "یه دسته", 216000));
            DB.SellLogs.Insert(new SellLog("d", UnixTimeTools.Now(), 5000, "دو دسته", 216000));
            DB.SellLogs.Insert(new SellLog("d", UnixTimeTools.Now(), 5000, "دو دسته", 216000));


            //search
            //  customer base
            Log.d("pay", DB.SellLogs.getHowMatchCustomerPay("guest"));
            Log.d("play", DB.SellLogs.getHowMatchCustomerPlay("guest"));
            Log.d("getWithCustomerId", DB.SellLogs.getWithCustomerId("guest"));

            //  service base
            Log.d("pay", DB.SellLogs.getAllServiceNames());
            Log.d("pay", DB.SellLogs.getHowMatchServiceIncome("دو دسته"));
            Log.d("play", DB.SellLogs.getHowMatchServicePlayed("دو دسته"));
            Log.d("getWithCustomerId", DB.SellLogs.getWithServiceName("دو دسته"));

        }

        public static void PagerTest(){

            DB.Init();

            var test = new DBPager(DBPager.MODE_NORMAL);

            test.setCallBack(new DBPager.PagerCallBack() {
                @Override
                public void callback(Object o) {
                    Log.d("tag" , o);
                }

                @Override
                public void onEnd() {
                    Log.d("end");
                }
            });

            DB.SellLogs.getWithPager(test);

            test.start();
        }
    }

    private static void DateTimeTest() {

        JalaliDateTime jdt = JalaliDateTime.Now();

        Log.d(jdt.toPersianString());
        Log.d(jdt.getPersianMonth());
        Log.d(jdt.getPersianSeason());
        Log.d(jdt.toUnixTime());
        Log.d("-----------------------");
        Log.d(jdt.getPersianDayOfWeek());
        Log.d(jdt.getDayOfWeek());

        Log.d("time", jdt.getTimePersianString(false));

        Log.d("jdt unix", jdt.toUnixTime());
        Log.d("jav unix", new Date().getTime());
        var unix = JalaliDateTime.Now().toUnixTime();
        Log.d("unix to jdt", new Date(unix).toString());

    }

    private static void CountBaseTest() {
        Log.d("\n-------CountBaseTest-------");
        CountBaseService cbs = new CountBaseService(15000);

        Log.d(cbs.getCurrentCost());
        cbs.increaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.increaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.decreaseCount();
        Log.d(cbs.getCurrentCost());
        cbs.increaseCount();
        Log.d(cbs.getCurrentCost());
    }

    private static void TimeBaseTest() {
        Log.d("\n-------TimeBaseTest-------");
        TimeBaseService tbs = new TimeBaseService(5000);

        new Thread(() -> {
            while (true) {
                Log.d(tbs.getCurrentCost());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.pause();
            Log.d("pause");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.resume();
            Log.d("resume");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.pause();
            Log.d("pause");
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tbs.resume();
            Log.d("resume");
        }).start();
    }
}