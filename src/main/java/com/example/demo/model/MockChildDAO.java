package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class MockChildDAO implements IChildDAO{
    private List<Child> children = new ArrayList<>();


    @Override
    public void createTable() {
        // This wil not be tested.
    }

    @Override
    public void insertChild(Child child) {
        // Validate required fields
        if (child.getFirstName() == null || child.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First Name is required");
        }
        if (child.getLastName() == null || child.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last Name is required");
        }
        if (child.getDateOfBirth() == null || child.getDateOfBirth().isEmpty()) {
            throw new IllegalArgumentException("Date of Birth is required");
        }
        if (child.getEmergencyContact() == null || child.getEmergencyContact().isEmpty()) {
            throw new IllegalArgumentException("Emergency Contact is required");
        }
        children.add(child);
    }

    @Override
    public List<Child> getAllChildren() {
        return children;
    }
}
