package com.gnm.desktop;

import com.gnm.desktop.core.Log;
import com.gnm.desktop.core.calculator.CountBaseService;
import com.gnm.desktop.core.calculator.TimeBaseService;
import com.gnm.desktop.data.DB;
import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.KeyValDb;
import com.gnm.desktop.data.model.TestModel;
import com.google.gson.Gson;

import javax.swing.text.html.ListView;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Test {

    public static void main(String[] args) {



        //SqliteTest();
        DBSpeedTest();
//        CountBaseTest();
//        TimeBaseTest();
    }

    private static void SqliteTest(){

        //make DAO object
        DB.Init(); //this should called before any database query

        Log.d("is empty: " + DB.testModel.IsEmpty()); //passed
        TestModel testRecord = new TestModel();

        testRecord.name = "ali";
        testRecord.Lname = "ghahremani";
        DB.testModel.Insert(testRecord); //passed

        testRecord.name = "علی";
        testRecord.Lname = "قهرمانی";
        DB.testModel.Insert(testRecord); //passed

        ShowContent(DB.testModel); //passed

        List<TestModel> lst = DB.testModel.getWithCondition(object -> {//passed
            TestModel t = (TestModel) object;
            return t.name.equals("ali");
        });

        Log.d("with condition result: "+ new Gson().toJson(lst.get(0)));

        lst.get(0).Lname = "updated name";
        DB.testModel.Update(lst.get(0));

        ShowContent(DB.testModel);

        DB.testModel.Remove(lst.get(0).getId());//passed

        ShowContent(DB.testModel);

        DB.testModel.Drop(); //passed
    }

    private static void DBSpeedTest(){

        DB.Init();

        List<TestModel> lst = new ArrayList<>();
        for (int i = 0 ; i < 10000 ; i++){
            TestModel tm = new TestModel();
            tm.name = UUID.randomUUID().toString();
            tm.Lname = UUID.randomUUID().toString();
            lst.add(tm);
        }

        long startTime = new Date().getTime();
        for (TestModel tm : lst){
            DB.testModel.Insert(tm);
        }
        long endTime = new Date().getTime();

        Log.d("insert time (in ms)",endTime-startTime);

        TestModel selected = DB.testModel.getAll().get(8000);

        startTime = new Date().getTime();
        List<TestModel> res = DB.testModel.getWithCondition(object -> ((TestModel) object).name.equals(selected.name));
        endTime = new Date().getTime();

        Log.d("search time (in ms)",endTime-startTime);
        Log.d("res",new Gson().toJson(res.get(0)));
    }

    private static void ShowContent(GenericDAO dao){

        Log.d("----content----");
        for(Object r : dao.getAll()){
            Log.d(new Gson().toJson(r));
        }
        Log.d("---------------");
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
