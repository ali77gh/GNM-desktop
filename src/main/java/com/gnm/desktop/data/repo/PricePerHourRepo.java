package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.PricePerHour;

public class PricePerHourRepo extends GenericDAO<PricePerHour> {

    public PricePerHourRepo() {
        super(PricePerHour.class, true);
    }
}
