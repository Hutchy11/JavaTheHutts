package com.example.demo.model;

import java.sql.*;

public class ChildDAO implements IChildDAO {
    private Connection connection;

    public ChildDAO() {
        connection = SqliteConnection.getInstance();
        createTable();
    }

    public void createTable() {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS child (
                ChildId TEXT PRIMARY KEY,
                ParentId TEXT NOT NULL,
                FirstName TEXT NOT NULL,
                LastName TEXT NOT NULL,
                DateOfBirth TEXT NOT NULL,
                Allergies TEXT,
                DietaryRequirements TEXT,
                EmergencyContact TEXT NOT NULL,
                FOREIGN KEY (ParentId) REFERENCES Carer(CarerId)
            );
        """;
        try (PreparedStatement pstmt = connection.prepareStatement(createTableSQL)) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertChild(Child child) throws SQLException {
        String query = "INSERT INTO child (ChildId, ParentId, FirstName, LastName, DateOfBirth, Allergies, DietaryRequirements, EmergencyContact) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, child.getChildId());
            pstmt.setString(2, child.getParentId());
            pstmt.setString(3, child.getFirstName());
            pstmt.setString(4, child.getLastName());
            pstmt.setString(5, child.getDateOfBirth());
            pstmt.setString(6, child.getAllergies());
            pstmt.setString(7, child.getDietaryRequirements());
            pstmt.setString(8, child.getEmergencyContact());
            pstmt.execute();
        }
    }
}
