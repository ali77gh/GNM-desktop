package com.gnm.desktop.data.repo;

import com.gnm.desktop.data.GenericDAO;
import com.gnm.desktop.data.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepo extends GenericDAO<Customer> {

    private static final int LIMIT = 7; //default output size limit

    public CustomerRepo() {
        super(Customer.class, true);
    }

    @Override
    public void Insert(Customer newRow) throws Exception {

        if (CheckPhoneExist(newRow.phone)) {
            throw new Exception("customer phone is already exist");
        }
        super.Insert(newRow);
    }

    public boolean CheckPhoneExist(String phone) {
        List<Customer> result = getWithCondition(object -> ((Customer) object).phone.equals(phone));
        return result.size() != 0;
    }

    /**
     * بخشی از شماره تلفنو بدی بهش کل کسایی که اون عدد تو شمارشون هستو بر میگردونه
     * ورودی حداقل دو رقمه
     */
    public List<Customer> getCustomerByPhonePrefix(String phone, int limit) {

        if (phone.length() < 2) new ArrayList<>();//enter minimum 2 chars

        List<Customer> result = getWithCondition(object -> ((Customer) object).phone.contains(phone));
        try {
            return result.subList(0, limit);
        } catch (IndexOutOfBoundsException e) {
            return result;
        }
    }

    public List<Customer> getCustomerByPhonePrefix(String phone) {
        return getCustomerByPhonePrefix(phone, LIMIT);
    }
}
