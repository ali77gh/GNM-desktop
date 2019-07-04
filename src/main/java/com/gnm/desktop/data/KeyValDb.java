package com.gnm.desktop.data;

import com.google.gson.Gson;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class KeyValDb {

    //todo defied id as primary key

    private final SQLiteDatabase DB;
    private final Gson gson;
    private final String table;

    /**
     * @param tableName create table if not exist
     */
    public KeyValDb(String tableName) {
        DB = new SQLiteDatabase();
        gson = new Gson();
        this.table = tableName;
        DB.execSQL(
                "CREATE TABLE IF NOT EXISTS " +
                        tableName + "(" +
                        "id VARCHAR ," +
                        "val VARCHAR" +
                        ");"
        );
    }

    public void insert(String id, Object object) {

        String strObj = gson.toJson(object);
        DB.execSQL(
                "INSERT INTO " + table + " VALUES('" + id + "','" + strObj + "');"
        );
    }

    public boolean IsEmpty() {
        Statement statement = DB.getStatement();

        try {
            ResultSet rs = statement.executeQuery("Select * from " + table + ";");
            boolean empty = !rs.next();
            rs.close();
            statement.close();
            return empty;
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Object Read(String id, Class<?> type) {
        Statement statement = DB.getStatement();

        try {
            ResultSet rs = statement.executeQuery("Select * from " + table + " where id='" + id + "';");
            if (rs.next()) {
                String strObj = rs.getString("val");
                rs.close();
                statement.close();
                return gson.fromJson(strObj, type);
            } else {
                throw new RuntimeException("record with id: " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public List<Object> ReadAllOfType(Class<?> type) {

        List<Object> validObjs = new ArrayList<>();
        Statement statement = DB.getStatement();

        String strObj;// it's out of loop for better performance
        Object obj;// it's out of loop for better performance
        try {
            ResultSet rs = statement.executeQuery("Select * from " + table + ";");
            while (rs.next()) {
                strObj = rs.getString("val");
                try {
                    obj = gson.fromJson(strObj, type);
                    validObjs.add(obj);
                } catch (Exception e) {/*ignore*/}
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return validObjs;
    }

    public List<Object> ReadWithCondition(Condition condition, Class<?> type) {

        List<Object> validObjs = new ArrayList<>();
        Statement statement = DB.getStatement();

        try {
            ResultSet rs = statement.executeQuery("Select * from " + table + ";");
            String strObj;
            while (rs.next()) {
                strObj = rs.getString("val");
                Object obj;
                try {
                    obj = gson.fromJson(strObj, type);
                } catch (Exception e) {
                    continue;
                }

                if (condition.IsConditionTrue(obj)) {
                    validObjs.add(obj);
                }
            }

            rs.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        return validObjs;
    }

    public void Update(String id, Object object) {

        String sql = "UPDATE " + table + " set val = '" + gson.toJson(object) + "' where id='" + id + "';";
        DB.execSQL(sql);
    }

    public void Remove(String id) {

        String sql = "DELETE from " + table + " where id='" + id + "';";
        DB.execSQL(sql);
    }

    public void Drop() {
        DB.execSQL("DROP TABLE IF EXISTS " + table);
    }

    public interface Condition {
        /**
         * this method effect on your performance make it fast
         *
         * @param object readed object
         * @return true if your condition is true else return false
         */
        boolean IsConditionTrue(Object object);
    }
}
