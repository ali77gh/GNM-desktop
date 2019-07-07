package com.gnm.desktop;

import com.gnm.desktop.core.Log;
import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.core.dateTime.JalaliDateTime;
import com.gnm.desktop.data.GenericDAO;
import com.google.gson.Gson;


public class Test {

    public static void main(String[] args) {


        //SqliteTest();

        DateTimeTest();

//        CountBaseTest();
//        TimeBaseTest();
    }

    private static void SqliteTest(){
//
//        //make DAO object
//        DB.Init(); //this should called before any database query
//
//        Log.d("is empty: " + DB.testModel.IsEmpty()); //passed
//        TestModel testRecord = new TestModel();
//
//        testRecord.name = "ali";
//        testRecord.Lname = "ghahremani";
//        DB.testModel.Insert(testRecord); //passed
//
//        testRecord.name = "علی";
//        testRecord.Lname = "قهرمانی";
//        DB.testModel.Insert(testRecord); //passed
//
//        ShowContent(DB.testModel); //passed
//
//        List<TestModel> lst = DB.testModel.getWithCondition(object -> {//passed
//            TestModel t = (TestModel) object;
//            return t.name.equals("ali");
//        });
//
//        Log.d("with condition result: "+ new Gson().toJson(lst.get(0)));
//
//        lst.get(0).Lname = "updated name";
//        DB.testModel.Update(lst.get(0));
//
//        ShowContent(DB.testModel);
//
//        DB.testModel.Remove(lst.get(0).getId());//passed
//
//        ShowContent(DB.testModel);
//
//        DB.testModel.Drop(); //passed
    }


    private static void ShowContent(GenericDAO dao){

        Log.d("----content----");
        for(Object r : dao.getAll()){
            Log.d(new Gson().toJson(r));
        }
        Log.d("---------------");
    }

    private static void DateTimeTest() {

        JalaliDateTime jdt = JalaliDateTime.Now();

        Log.d(jdt.toPersianString());
        Log.d(jdt.getPersianMonth());
        Log.d(jdt.getPersianSeason());
        Log.d(jdt.getPersianDayOfWeek());
        Log.d(jdt.getDayOfWeek());
        Log.d(jdt.toUnixTime());

    }

    private static void CountBaseTest(){
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

    private static void TimeBaseTest(){
        Log.d("\n-------TimeBaseTest-------");
        TimeBaseService tbs = new TimeBaseService(5000);

        new Thread(() -> {
            while(true){
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
