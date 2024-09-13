package model.dao;

import model.DbConfig;
import model.entities.Client;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class ClientDAO {

    public void addClient(Client client) {
        String sql = "INSERT INTO Client (first_name, second_name, phone_number, email, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getSecondName());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setString(4, client.getEmail());
            stmt.setString(5, client.getPassword());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client client) {
        List<String> sqlColumns = new ArrayList<>();
        int counter = 0;

        if (client.getFirstName() != null) {
            sqlColumns.add("first_name = ?");
            counter++;
        }
        if (client.getSecondName() != null) {
            sqlColumns.add("second_name = ?");
            counter++;
        }
        if (client.getPhoneNumber() != null) {
            sqlColumns.add("phone_number = ?");
            counter++;
        }
        if (client.getEmail() != null) {
            sqlColumns.add("email = ?");
            counter++;
        }
        if (client.getPassword() != null) {
            sqlColumns.add("password = ?");
            counter++;
        }

        if (counter == 0) {
            return;
        }

        String sql = "UPDATE Client SET ";
        sql += String.join(", ", sqlColumns);
        sql += " WHERE id = ?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int parameterIndex = 1;

            if (client.getFirstName() != null) {
                stmt.setString(parameterIndex++, client.getFirstName());
            }
            if (client.getSecondName() != null) {
                stmt.setString(parameterIndex++, client.getSecondName());
            }
            if (client.getPhoneNumber() != null) {
                stmt.setString(parameterIndex++, client.getPhoneNumber());
            }
            if (client.getEmail() != null) {
                stmt.setString(parameterIndex++, client.getEmail());
            }
            if (client.getPassword() != null) {
                stmt.setString(parameterIndex++, client.getPassword());
            }
            // Set the ID for the WHERE clause
            stmt.setInt(parameterIndex, client.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteClient(Client client) {

    }

    public void searchClient() {

    }

    public List<Client> listAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM Client";
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Client client = new Client(
                    rs.getInt("id"),
                    rs.getString("first_name"),
                    rs.getString("second_name"),
                    rs.getString("phone_number"),
                    rs.getString("email"), 
                    rs.getString("password")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public Integer checkAccountInfo(String email, String password) {
        String selectSql = "SELECT id FROM Client WHERE email = ? AND password = ?";
        String deleteSql = "DELETE FROM user_session"; 
        String insertSql = "INSERT INTO user_session (user_id) VALUES (?)"; 

        try (Connection conn = DbConfig.getConnection()) {
            conn.setAutoCommit(false);
    
            try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
                selectStmt.setString(1, email);
                selectStmt.setString(2, password);
                ResultSet rs = selectStmt.executeQuery();
    
                if (rs.next()) {
                    Integer userId = rs.getInt("id");
    
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                        deleteStmt.executeUpdate();
                    }
    
                    try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                        insertStmt.setInt(1, userId);
                        insertStmt.executeUpdate();
                    }
    
                    conn.commit();
                    
                    return userId;
                } else {
                    conn.rollback(); 
                    return null;
                }
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
                return null;
            } finally {
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}