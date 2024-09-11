package com.example.demo.model;

public class MockCarerDAO implements ICarerDAO {
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
}
