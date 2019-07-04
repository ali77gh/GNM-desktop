package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.TestModel;

public class TestModelDAO extends GenericDAO<TestModel> {

    public TestModelDAO() {
        super(TestModel.class, "testModel", true);
    }
}
