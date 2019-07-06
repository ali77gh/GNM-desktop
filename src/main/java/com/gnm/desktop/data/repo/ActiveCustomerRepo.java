package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.ActiveCustomer;

public class ActiveCustomerRepo extends GenericDAO<ActiveCustomer> {

    public ActiveCustomerRepo() {
        super(ActiveCustomer.class, true);
    }
}
