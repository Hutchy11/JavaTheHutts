package com.example.demo.model;

import java.util.List;

public interface IChildDAO {
    // Create table (real DAO will implement this to create the table in the DB)
    void createTable();

    void insertChild(Child child);

    List<Child> getAllChildren();
}
