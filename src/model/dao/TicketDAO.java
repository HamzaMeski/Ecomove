package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.DbConfig;
import model.entities.Ticket;
import model.enums.TransportType;
import model.enums.TicketStatus;

import java.sql.*;
import java.time.LocalDate;

public class TicketDAO {
    public void addTicket(Ticket ticket) {
        System.out.println("##########??");
        System.out.println(ticket);
        System.out.println("##########??");
        
        String sql = "INSERT INTO Ticket (transport_type, purchase_price, sale_price, sale_date, status, contract_id) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set all the parameters for the PreparedStatement
            stmt.setString(1, ticket.getTransportType().toString().toUpperCase());
            stmt.setFloat(2, ticket.getPurchasePrice());
            stmt.setFloat(3, ticket.getSalePrice());
            stmt.setDate(4, Date.valueOf(ticket.getSaleDate()));
            stmt.setString(5, ticket.getTicketStatus().toString().toUpperCase());
            stmt.setInt(6, ticket.getContractId());

            // Execute the insert statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateTicket(Ticket ticket) {
        List<String> sqlColumns = new ArrayList<>();
        int counter = 0;

        if (ticket.getTransportType() != null) {
            sqlColumns.add("transport_type = ?");
            counter++;
        }
        if (ticket.getPurchasePrice() != null) {
            sqlColumns.add("purchase_price = ?");
            counter++;
        }
        if (ticket.getSalePrice() != null) {
            sqlColumns.add("sale_price = ?");
            counter++;
        }
        if (ticket.getSaleDate() != null) {
            sqlColumns.add("sale_date = ?");
            counter++;
        }
        if (ticket.getTicketStatus() != null) {
            sqlColumns.add("status = ?");
            counter++;
        }

        if (counter == 0) {
            return;
        }

        String sql = "UPDATE Ticket SET ";
        sql += String.join(", ", sqlColumns);
        sql += " WHERE id = ?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            int parameterIndex = 1;

            if (ticket.getTransportType() != null) {
                stmt.setString(parameterIndex++, ticket.getTransportType().toString());
            }
            if (ticket.getPurchasePrice() != null) {
                stmt.setFloat(parameterIndex++, ticket.getPurchasePrice());
            }
            if (ticket.getSalePrice() != null) {
                stmt.setFloat(parameterIndex++, ticket.getSalePrice());
            }
            if (ticket.getSaleDate() != null) {
                stmt.setDate(parameterIndex++, Date.valueOf(ticket.getSaleDate()));
            }
            if (ticket.getTicketStatus() != null) {
                stmt.setString(parameterIndex++, ticket.getTicketStatus().toString());
            }

            // Set the ID for the WHERE clause
            stmt.setInt(parameterIndex, ticket.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> listAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM Ticket";
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ticket ticket = new Ticket(
                    rs.getInt("id"),
                    TransportType.valueOf(rs.getString("transport_type")),
                    rs.getFloat("purchase_price"),
                    rs.getFloat("sale_price"),
                    rs.getDate("sale_date").toLocalDate(),
                    TicketStatus.valueOf(rs.getString("status")),
                    rs.getInt("contract_id")
                );
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
