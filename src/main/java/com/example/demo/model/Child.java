package com.example.demo.model;

public class Child {
    private String childId; // Automatically generate UUID for staffId
    private String parentId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String allergies;
    private String dietaryRequirements;
    private String emergencyContact;

    // Constructor
    public Child(String childId, String parentId, String firstName, String lastName, String dateOfBirth,
                 String allergies, String dietaryRequirements, String emergencyContact) {
        this.childId = childId;
        this.parentId = parentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.allergies = allergies;
        this.dietaryRequirements = dietaryRequirements;
        this.emergencyContact = emergencyContact;
    }

    // Getters and Setters
    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
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

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getDietaryRequirements() {
        return dietaryRequirements;
    }

    public void setDietaryRequirements(String dietaryRequirements) {
        this.dietaryRequirements = dietaryRequirements;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
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
