package com.example.demo.model;

/**
 * Controller class for the Register Carer View.
 * Handles the registration of a new carer.
 */
public class Session {
    private static Carer loggedCarer;
    private static Staff loggedStaff;

    // Set the logged-in Carer
    public static void setLoggedCarer(Carer carer) {
        loggedCarer = carer;
    }

    // Get the logged-in Carer
    public static Carer getLoggedCarer() {
        return loggedCarer;
    }

    // Set the logged-in Staff
    public static void setLoggedStaff(Staff staff) {
        loggedStaff = staff;
    }

    // Get the logged-in Staff
    public static Staff getLoggedStaff() {
        return loggedStaff;
    }

    /**
     * Clear the logged-in Carer and Staff.
     */
    public static void clear() {
        loggedCarer = null;
        loggedStaff = null;
    }
}

