package com.example.demo.model;

public class MockStaffDAO implements IStaffDAO{
    @Override
    public void createTable() {
        // This wil not be tested.
    }

    @Override
    public Staff login(String email, String password) {
        // Check if the login credentials are valid
        if ("staff@example.com".equals(email) && "staffpassword".equals(password)) {
            // Return a new Staff object with mock data
            return new Staff(
                    "1234-5678-9012",   // Staff ID
                    "John",             // First Name
                    "Doe",              // Last Name
                    "staff@example.com",// Email
                    "staffpassword",    // Password
                    "0412345678",       // Phone
                    "Manager",          // Role
                    "2023-01-01"        // Hire Date
            );
        }
        return null; // Return null if login credentials are invalid
    }
}
