package com.example.demo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for Staff.
 * Handles database operations related to staff members.
 */
public class StaffDAO implements IStaffDAO {
    private Connection connection;

    /**
     * Constructor for the StaffDAO class.
     * Initializes the database connection and creates the staff table if it doesn't exist.
     */
    public StaffDAO(){
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the staff table in the database if it doesn't exist.
     */
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

    /**
     * Logs in a staff member by verifying their email and password.
     *
     * @param email the email of the staff member
     * @param password the password of the staff member
     * @return the Staff object if login is successful, null otherwise
     */
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

    /**
     * Registers a new staff member in the database.
     *
     * @param staffId the unique ID of the staff member
     * @param firstName the first name of the staff member
     * @param lastName the last name of the staff member
     * @param email the email of the staff member
     * @param password the password of the staff member
     * @param phone the phone number of the staff member
     * @param role the role of the staff member
     * @param hireDate the hire date of the staff member
     * @return true if the registration is successful, false otherwise
     */
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

    /**
     * Retrieves all staff members from the database.
     *
     * @return a list of Staff objects
     */
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

    public void updateStaff(Staff staff) {
        String sql = "UPDATE staff SET FirstName = ?, LastName = ?, Email = ?, Password = ?, Phone = ?, Role = ?, HireDate = ? WHERE StaffId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, staff.getFirstName());
            pstmt.setString(2, staff.getLastName());
            pstmt.setString(3, staff.getEmail());
            pstmt.setString(4, staff.getPassword());
            pstmt.setString(5, staff.getPhone());
            pstmt.setString(6, staff.getRole());
            pstmt.setString(7, staff.getHireDate());
            pstmt.setString(8, staff.getStaffId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
