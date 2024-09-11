package com.example.demo.model;

import java.sql.*;
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

}
