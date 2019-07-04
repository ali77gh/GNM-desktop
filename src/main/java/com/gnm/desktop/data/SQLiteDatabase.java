package com.gnm.desktop.data;


import com.gnm.desktop.core.Log;

import java.sql.*;

public class SQLiteDatabase {

    private Connection c;

    private static final String DB_NAME = "gnm";

    public SQLiteDatabase(){

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME + ".db");
            Log.d("database successfully Opened");
        } catch (Exception e) {
            throw new RuntimeException(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public void close(){
        try {
            c.close();
        }catch (java.sql.SQLException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void execSQL(String sql){

        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Statement getStatement(){
        try {
            return c.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
