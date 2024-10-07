package com.example.demo.model;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;


public class CarerDAO implements ICarerDAO {
    private Connection connection;

    public CarerDAO(){
        connection = SqliteConnection.getInstance();
        createTable();
    }

    // Create table if it doesn't exist
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

    // Method for carer login
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

    // Fetch all carers from the database
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

    // Method to register a new carer
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

    // Method to update a carer
    public void updateCarer(Carer carer) {
        String sql = "UPDATE carer SET FirstName = ?, LastName = ?, Email = ?, Phone = ?, Address = ?, Password = ? WHERE CarerId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, carer.getFirstName());
            pstmt.setString(2, carer.getLastName());
            pstmt.setString(3, carer.getEmail());
            pstmt.setString(4, carer.getPhone());
            pstmt.setString(5, carer.getAddress());
            pstmt.setString(6, carer.getPassword());
            pstmt.setString(7, carer.getCarerId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
