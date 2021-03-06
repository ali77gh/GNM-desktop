package com.gnm.desktop.data;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GenericDAO<T extends Model> {

    private KeyValDb db;
    private Class type;

    private boolean autoSetId;

    public GenericDAO(Class type, String tableName,boolean autoSetId) {
        db = new KeyValDb(tableName);
        this.autoSetId = autoSetId;
        this.type = type;
    }

    public GenericDAO(Class type,boolean autoSetId) {
        db = new KeyValDb(type.getSimpleName());
        this.autoSetId = autoSetId;
        this.type = type;
    }

    //Read

    public List<T> getAll() {
        ArrayList<T> list = new ArrayList<>();
        for (Object o : db.ReadAllOfType(type))
            list.add((T) o);
        return (list);
    }

    public T getById(String id) {
        return (T) db.Read(id, type);
    }

    public List<T> getWithCondition(KeyValDb.Condition condition){
        ArrayList<T> list = new ArrayList<>();
        for (Object o : db.ReadWithCondition(condition,type))
            list.add((T) o);
        return (list);
    }

    public void getWithPager(DBPager dbPager){
        db.getWithPager(dbPager);
    }

    public boolean IsEmpty() {
        return db.IsEmpty();
    }

    //Write
    public void Insert(T newRow) {
        if (autoSetId)
            newRow.setId(UUID.randomUUID().toString());
        db.insert(newRow.getId(), newRow);
    }

    public void Update(T newRow) {
        db.Update(newRow.getId(), newRow);
    }

    public void Remove(String id) {
        db.Remove(id);
    }

    public void Drop(){
        db.Drop();
    }
}