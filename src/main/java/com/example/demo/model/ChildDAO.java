package com.example.demo.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void insertChild(Child child) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Child> getAllChildren() {
        List<Child> children = new ArrayList<>();
        String query = "SELECT * FROM child";

        try (Statement stmt = connection.createStatement();
             ResultSet resultSet = stmt.executeQuery(query)) {

            while (resultSet.next()) {
                Child child = new Child(
                        resultSet.getString("ChildId"),
                        resultSet.getString("ParentId"),
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Allergies"),
                        resultSet.getString("DietaryRequirements"),
                        resultSet.getString("EmergencyContact")
                );
                children.add(child);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return children;
    }

    public List<Child> getChildrenByParent(String parentId) {
        List<Child> children = new ArrayList<>();
        String query = "SELECT * FROM child WHERE ParentId = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, parentId);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Child child = new Child(
                            resultSet.getString("ChildId"),
                            resultSet.getString("ParentId"),
                            resultSet.getString("FirstName"),
                            resultSet.getString("LastName"),
                            resultSet.getString("DateOfBirth"),
                            resultSet.getString("Allergies"),
                            resultSet.getString("DietaryRequirements"),
                            resultSet.getString("EmergencyContact")
                    );
                    children.add(child);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return children;
    }

    public void updateChild(Child child) {
        String sql = "UPDATE child SET FirstName = ?, LastName = ?, DateOfBirth = ?, Allergies = ?, DietaryRequirements = ?, EmergencyContact = ? WHERE ChildId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, child.getFirstName());
            pstmt.setString(2, child.getLastName());
            pstmt.setString(3, child.getDateOfBirth());
            pstmt.setString(4, child.getAllergies());
            pstmt.setString(5, child.getDietaryRequirements());
            pstmt.setString(6, child.getEmergencyContact());
            pstmt.setString(7, child.getChildId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}