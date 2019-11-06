package com.gnm.desktop.data;

import com.gnm.desktop.data.repo.*;

public class DB {

    public static ActiveCustomerRepo ActiveCustomers;
    public static CountBaseAutoCompleteRepo CountBaseAutoComplete;
    public static CustomerRepo Customers;
    public static PricePerHourRepo Prices;
    public static SellLogRepo SellLogs;
    //add more Repo class here
    //       .
    //       .
    //       .

    public static void Init(){
        SQLiteDatabase.Init();
        DB.ActiveCustomers = new ActiveCustomerRepo();
        DB.CountBaseAutoComplete = new CountBaseAutoCompleteRepo();
        DB.Customers = new CustomerRepo();
        DB.Prices = new PricePerHourRepo();
        DB.SellLogs = new SellLogRepo();
        //then call constructor here
        //       .
        //       .
        //       .
    }
}
