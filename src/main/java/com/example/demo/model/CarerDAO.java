package com.example.demo.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for Carer.
 * Handles database operations related to carers.
 */
public class CarerDAO implements ICarerDAO {
    private Connection connection;

    /**
     * Constructor for the CarerDAO class.
     * Initializes the database connection and creates the carer table if it doesn't exist.
     */
    public CarerDAO(){
        connection = SqliteConnection.getInstance();
        createTable();
    }

    /**
     * Creates the carer table in the database if it doesn't exist.
     */
    public void createTable() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS carer (
                CarerId TEXT PRIMARY KEY,
                FirstName TEXT NOT NULL,
                LastName TEXT NOT NULL,
                Email TEXT NOT NULL,
                Password TEXT NOT NULL,
                Phone TEXT NOT NULL,
                Address TEXT NOT NULL
            );
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Logs in a carer by checking the email and password.
     *
     * @param email the email of the carer
     * @param password the password of the carer
     * @return the Carer object if login is successful, null otherwise
     */
    public Carer login(String email, String password) {
        String query = "SELECT * FROM carer WHERE Email = ? AND Password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return new Carer(
                        resultSet.getString("CarerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Fetches all carers from the database.
     *
     * @return a list of Carer objects
     */
    public List<Carer> getAllCarers() {
        List<Carer> carers = new ArrayList<>();
        String query = "SELECT * FROM carer";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Carer carer = new Carer(
                        resultSet.getString("CarerId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("Phone"),
                        resultSet.getString("Address")
                );
                carers.add(carer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carers;
    }

    /**
     * Registers a new carer in the database.
     *
     * @param carerId the unique ID of the carer
     * @param firstName the first name of the carer
     * @param lastName the last name of the carer
     * @param email the email address of the carer
     * @param password the password of the carer
     * @param phone the phone number of the carer
     * @param address the address of the carer
     * @return true if the registration is successful, false otherwise
     */
    public boolean registerCarer(String carerId, String firstName, String lastName, String email, String password, String phone, String address) {
        String insertSQL = """
            INSERT INTO carer (CarerId, FirstName, LastName, Email, Password, Phone, Address)
            VALUES (?, ?, ?, ?, ?, ?, ?);
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, carerId);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, email);
            pstmt.setString(5, password);
            pstmt.setString(6, phone);
            pstmt.setString(7, address);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Updates the details of an existing carer in the database.
     *
     * @param loggedCarer the Carer object containing updated details
     */
    public void updateCarer(Carer loggedCarer) {
        String updateSQL = """
                    UPDATE carer
                    SET FirstName = ?, LastName = ?, Email = ?, Password = ?, Phone = ?, Address = ?
                    WHERE CarerId = ?;
                """;
        try (PreparedStatement pstmt = connection.prepareStatement(updateSQL)) {
            pstmt.setString(1, loggedCarer.getFirstName());
            pstmt.setString(2, loggedCarer.getLastName());
            pstmt.setString(3, loggedCarer.getEmail());
            pstmt.setString(4, loggedCarer.getPassword());
            pstmt.setString(5, loggedCarer.getPhone());
            pstmt.setString(6, loggedCarer.getAddress());
            pstmt.setString(7, loggedCarer.getCarerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
