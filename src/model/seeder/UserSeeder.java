package model.seeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class UserSeeder {
    
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/";
        String username = "postgres";
        String password = "password";
        String dbName = "seeder_meski";

        Connection connection = null;
        Statement statement = null;

        try {
            // Connect to PostgreSQL server
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();

            // Create the new database
            String sqlCreateDatabase = "CREATE DATABASE " + dbName;
            statement.executeUpdate(sqlCreateDatabase);
            System.out.println("Database created successfully: " + dbName);

            // Switch to the newly created database
            connection.close();
            jdbcUrl = "jdbc:postgresql://localhost:5432/" + dbName;
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            statement = connection.createStatement();

            // Create a users table
            String sqlCreateTable = "CREATE TABLE users (" +
                                    "id SERIAL PRIMARY KEY, " +
                                    "username VARCHAR(50) NOT NULL, " +
                                    "password VARCHAR(50) NOT NULL, " +
                                    "email VARCHAR(50) NOT NULL)";
            statement.executeUpdate(sqlCreateTable);
            System.out.println("Table 'users' created successfully.");

            // Insert sample data
            String sqlInsertData = "INSERT INTO users (username, password, email) VALUES " +
                                   "('john_doe', 'password123', 'john@example.com')," +
                                   "('jane_doe', 'securepassword', 'jane@example.com')";
            statement.executeUpdate(sqlInsertData);
            System.out.println("Sample data inserted successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
