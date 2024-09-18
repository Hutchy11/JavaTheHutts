package com.example.demo.model;

public class Carer {
    private String carerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private String address;

    public Carer(String carerId, String firstName, String lastName, String email, String password, String phone, String address) {
        this.carerId = carerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    // Getter and Setter for carerId
    public String getCarerId() {
        return carerId;
    }

    public void setCarerId(String carerId) {
        this.carerId = carerId;
    }

    // Getter and Setter for firstName
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Getter and Setter for lastName
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Getter and Setter for email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter and Setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter and Setter for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Method to get the full name (already defined)
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return getFullName(); // Returns the full name when the Carer object is converted to a string
    }
}
