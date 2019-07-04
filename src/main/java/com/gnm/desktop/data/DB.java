package com.gnm.desktop.data;

import com.gnm.desktop.data.repo.TestModelDAO;

public class DB {

    public static TestModelDAO testModel;
    //add more models here
    //       .
    //       .
    //       .

    public static void Init(){
        testModel = new TestModelDAO();
        //add more models here
        //       .
        //       .
        //       .
    }
}
