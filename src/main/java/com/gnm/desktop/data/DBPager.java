package com.gnm.desktop.data;

import javafx.application.Platform;

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

    public DBPager(int mode) {
        this.mode = mode;
    }

    public DBPager() {
        this(MODE_NORMAL);
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
                        callBack.callback(resultSet.getString("val"));
                    break;
                case MODE_REVERSE:
                    resultSet.last();
                    while (resultSet.previous() && !isStoped)
                        callBack.callback(resultSet.getString("val"));
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