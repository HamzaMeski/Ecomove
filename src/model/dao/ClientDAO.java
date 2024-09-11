package model.dao;

import model.DbConfig;
import model.entities.Client;
import model.entities.Ticket;
import model.enums.TicketStatus;
import model.enums.TransportType;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class ClientDAO {

    public void addClient(Client client) {
        String sql = "INSERT INTO Client (first_name, second_name, phone_number, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, client.getFirstName());
            stmt.setString(2, client.getSecondName());
            stmt.setString(3, client.getPhoneNumber());
            stmt.setString(4, client.getEmail());
    
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
            if (client.getFirstName() != null) {
                stmt.setString(parameterIndex++, client.getSecondName());
            }
            if (client.getFirstName() != null) {
                stmt.setString(parameterIndex++, client.getPhoneNumber());
            }
            if (client.getFirstName() != null) {
                stmt.setString(parameterIndex++, client.getEmail());
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
                    rs.getString("email")
                );
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // System.out.println("hi hamza");
        // System.out.println(clients);
        return clients;
    }
}