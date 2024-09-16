package com.example.demo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class StaffDAO implements IStaffDAO {
    private Connection connection;

    public StaffDAO(){
        connection = SqliteConnection.getInstance();
        createTable();
    }

    public void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS staff ("
                + "StaffId TEXT PRIMARY KEY, "       // UUID probably
                + "FirstName TEXT NOT NULL, "
                + "LastName TEXT NOT NULL, "
                + "Email TEXT NOT NULL, "
                + "Password TEXT NOT NULL, "
                + "Phone TEXT NOT NULL, "
                + "Role TEXT NOT NULL, "
                + "HireDate TEXT NOT NULL"
                + ");";

        try (Statement stmt = connection.createStatement()) {
            // Execute the SQL statement to create the table
            stmt.execute(sql);
            System.out.println("Staff table created or already exists.");
        } catch ( SQLException e) {
            System.err.println("Error creating staff table: " + e.getMessage());
        }
    }

    public Staff login(String email, String password) {
        String query = "SELECT * FROM staff WHERE Email = ? AND Password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                // Staff found, create and return Staff object
                return new Staff(
                        resultSet.getString("StaffId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Role"),
                        resultSet.getString("HireDate")
                );
            } else {
                // No staff found with the given credentials
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error during login: " + e.getMessage());
            return null;
        }
    }

    // Method to register a new staff
    public boolean registerStaff(String staffId, String firstName, String lastName, String email, String password, String phone, String role, String hireDate) {
        String insertSQL = """
            INSERT INTO staff (StaffId, FirstName, LastName, Email, Password, Phone, Role, HireDate)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?);
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, staffId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, phone);
            pstmt.setString(7, role);
            pstmt.setString(8, hireDate);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Staff> getAllStaffs() {
        List<Staff> staffs = new ArrayList<>();
        String query = "SELECT * FROM staff";
        Staff staff = null;
        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {
            while (resultSet.next()) {
                staff = new Staff(
                        resultSet.getString("StaffId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Role"),
                        resultSet.getString("HireDate")
                );
                staffs.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffs;
    }

}
