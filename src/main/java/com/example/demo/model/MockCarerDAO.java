package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class MockCarerDAO implements ICarerDAO {

    List<Carer> carers = new ArrayList<>();

    @Override
    public void createTable() {
        // This wil not be tested.
    }

    @Override
    public Carer login(String email, String password) {
        if ("carer@example.com".equals(email) && "carerpassword".equals(password)) {
            return new Carer("1", "Jane", "Doe", email, password, "123456789", "123 Street");
        }
        return null;
    }

    @Override
    public List<Carer> getAllCarers() {
        // Return a hardcoded list of Carers for testing

        carers.add(new Carer("1", "Jane", "Doe", "carer1@example.com", "password1", "123456789", "123 Street"));
        carers.add(new Carer("2", "John", "Smith", "carer2@example.com", "password2", "987654321", "456 Avenue"));
        carers.add(new Carer("3", "Mary", "Johnson", "carer3@example.com", "password3", "1122334455", "789 Boulevard"));
        return carers;
    }
}
