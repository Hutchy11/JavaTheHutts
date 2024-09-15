package com.example.demo.model;

public interface ICarerDAO {
    // Create table (real DAO will implement this to create the table in the DB)
    void createTable();

    // Method for carer login
    Carer login(String email, String password);
}

