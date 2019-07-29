package com.gnm.desktop.data;


import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBPager {

    public static final int MODE_NORMAL = 100;
    public static final int MODE_REVERSE = 101;


    private PagerCallBack callBack;
    private OnPagerEnd onPagerEnd;

    private ResultSet resultSet;
    private int mode;
    private boolean isStoped = false;
    private Class<?> type;

    public DBPager(Class<?> type, int mode) {
        this.mode = mode;
        this.type = type;
    }

    public DBPager(Class<?> type) {
        this(type, MODE_NORMAL);
    }

    public void setCallBack(PagerCallBack callBack) {
        this.callBack = callBack;
    }

    void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    void setOnPagerEnd(OnPagerEnd onPagerEnd) {
        this.onPagerEnd = onPagerEnd;
    }


    private Thread pagerThread = new Thread(() -> {
        try {
            switch (mode){
                case MODE_NORMAL:
                    while (resultSet.next() && !isStoped)
                        callBack.callback(new Gson().fromJson(resultSet.getString("val"), type));
                    break;
                case MODE_REVERSE:
                    resultSet.last();
                    while (resultSet.previous() && !isStoped)
                        callBack.callback(new Gson().fromJson(resultSet.getString("val"), type));
                    break;
                default:
                    throw new RuntimeException("invalid code");
            }
            resultSet.close();
            callBack.onEnd();
            onPagerEnd.onEnd();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    });

    public void start() {
        pagerThread.start();
    }

    public void stop() {
        isStoped = true;
    }

    public interface PagerCallBack {
        void callback(Object o);
        void onEnd();
    }

    public interface OnPagerEnd{
        void onEnd();
    }
}