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
        children.add(child);
    }

    @Override
    public List<Child> getAllChildren() {
        return List.of();
    }
}
