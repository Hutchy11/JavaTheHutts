package com.example.demo.model;

public interface IStaffDAO {
    void createTable();

    // Method for carer login
    Staff login(String email, String password);
}
