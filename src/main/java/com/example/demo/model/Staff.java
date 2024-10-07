package com.example.demo.model;

import java.util.UUID;

/**
 * Model class representing a Staff member.
 * Contains information about a staff member and provides methods to access and modify this information.
 */
public class Staff {
    private String staffId;    // UUID for StaffId
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String role;
    private String hireDate;

    /**
     * Constructor for the Staff class.
     * Initializes a new Staff object with the specified details.
     *
     * @param staffId the unique ID of the staff member
     * @param firstName the first name of the staff member
     * @param lastName the last name of the staff member
     * @param email the email of the staff member
     * @param password the password of the staff member
     * @param phone the phone number of the staff member
     * @param role the role of the staff member
     * @param hireDate the hire date of the staff member
     */
    public Staff(String staffId, String firstName, String lastName, String email, String password, String phone, String role, String hireDate) {
        this.staffId = staffId; // Automatically generate UUID for staffId
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.role = role;
        this.hireDate = hireDate;
    }

    /**
     * Generates a new UUID for the staff member.
     *
     * @return a new UUID as a string
     */
    private String generateUUID() {
        return UUID.randomUUID().toString();
    }

    // Getters and Setters
    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    // Method to return the full name
    public String getFullName() {
        return firstName + " " + lastName;
    }
}