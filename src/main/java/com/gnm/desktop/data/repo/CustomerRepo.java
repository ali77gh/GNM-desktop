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
    public void Insert(Customer newRow) {

        if (CheckPhoneExist(newRow.phone)) {
            throw new RuntimeException("customer phone is already exist");
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
    public List<Customer> getCustomerByPhoneContains(String phone, int limit) {

        if (phone.length() < 2) throw new RuntimeException();//enter minimum 2 chars

        List<Customer> result = getWithCondition(object -> ((Customer) object).phone.contains(phone));
        try {
            return result.subList(0, limit);
        } catch (IndexOutOfBoundsException e) {
            return result;
        }
    }

    public List<Customer> getCustomerByPhoneContains(String phone) {
        return getCustomerByPhoneContains(phone, LIMIT);
    }

    /**
     * افزایش اعتبار مشتری
     */
    public void IncreaseCredit(String id, int cost) {
        Customer customer = getById(id);
        customer.credit += cost;
        Update(customer);
    }

    /**
     * کاهش اعتبار مشتری
     */
    public void DecreaseCredit(String id, int cost) {
        Customer customer = getById(id);
        customer.credit -= cost;
        Update(customer);
    }

    public List<Customer> searchByGame(String gameName) {

        return getWithCondition(object -> {
            var customer = ((Customer) object);
            return customer.games.indexOf(gameName) != -1;
        });
    }

    public List<String> getGames() {
        var result = new ArrayList<String>();

        getWithCondition(object -> {
            var customer = ((Customer) object);
            for (var game : customer.games) {
                if (result.indexOf(game) == -1)
                    result.add(game);
            }
            return false;
        });

        return null;
    }
}
