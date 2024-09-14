package com.example.demo.model;

import java.util.List;

public interface ICarerDAO {
    // Create table (real DAO will implement this to create the table in the DB)
    void createTable();

    // Method for carer login
    Carer login(String email, String password);

    List<Carer> getAllCarers();
}

