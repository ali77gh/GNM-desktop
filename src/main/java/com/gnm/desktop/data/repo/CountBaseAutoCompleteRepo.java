package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.CountBaseAutoComplete;

import java.util.ArrayList;
import java.util.List;

public class CountBaseAutoCompleteRepo extends GenericDAO<CountBaseAutoComplete> {

    private static final int LIMIT = 7; //default output size limit

    public CountBaseAutoCompleteRepo() {
        super(CountBaseAutoComplete.class, true);
    }

    /**
     * اول اسمشو به ورودی بدی پیشنهاد میده
     *
     * @param limit تعداد خروجی ها
     */
    public List<CountBaseAutoComplete> getWithPrefix(String prefix, int limit) {

        if (prefix.length() < 1) new ArrayList<>(); //enter minimum 1 chars

        List<CountBaseAutoComplete> result = getWithCondition(object -> ((CountBaseAutoComplete) object).name.contains(prefix));

        try {
            return result.subList(0, limit);
        } catch (IndexOutOfBoundsException e) {
            return result;
        }
    }

    /**
     * اول اسمشو به ورودی بدی پیشنهاد میده
     * تعداد خروجی ها رو از دیفالت میگیره
     */
    public List<CountBaseAutoComplete> getWithPrefix(String prefix) {
        return getWithPrefix(prefix, LIMIT);
    }
}