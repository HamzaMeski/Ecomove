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
        String sql = "INSERT INTO Ticket (transport_type, purchase_price, sale_price, sale_date, status, departure, departure_time, destination, destination_time, contract_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (
                Connection conn = DbConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
            ) {

            // Set all the parameters for the PreparedStatement
            stmt.setString(1, ticket.getTransportType().toString().toUpperCase());
            stmt.setFloat(2, ticket.getPurchasePrice());
            stmt.setFloat(3, ticket.getSalePrice());
            stmt.setDate(4, null);
            stmt.setString(5, ticket.getTicketStatus().toString().toUpperCase());
            stmt.setString(6, ticket.getDeparture());
            stmt.setTimestamp(7, Timestamp.valueOf(ticket.getDepartureTime()));
            stmt.setString(8, ticket.getDestination());
            stmt.setTimestamp(9, Timestamp.valueOf(ticket.getDestinationTime()));
            stmt.setInt(10, ticket.getContractId());

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
        if (ticket.getDeparture() != null) {
            sqlColumns.add("departure = ?");
            counter++;
        }
        if (ticket.getDepartureTime() != null) {
            sqlColumns.add("departure_time = ?");
            counter++;
        }
        if (ticket.getDestination() != null) {
            sqlColumns.add("destination = ?");
            counter++;
        }
        if (ticket.getDestinationTime() != null) {
            sqlColumns.add("destination_time = ?");
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
            if (ticket.getDeparture() != null) {
                stmt.setString(parameterIndex++, ticket.getDeparture());
            }
            if (ticket.getDepartureTime() != null) {
                stmt.setTimestamp(parameterIndex++, Timestamp.valueOf(ticket.getDepartureTime()));
            }
            if (ticket.getDestination() != null) {
                stmt.setString(parameterIndex++, ticket.getDestination());
            }
            if (ticket.getDestinationTime() != null) {
                stmt.setTimestamp(parameterIndex++, Timestamp.valueOf(ticket.getDestinationTime()));
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
                    rs.getDate("sale_date") != null ? rs.getDate("sale_date").toLocalDate(): null,
                    TicketStatus.valueOf(rs.getString("status")),
                    rs.getString("departure"),
                    rs.getTimestamp("departure_time").toLocalDateTime(),
                    rs.getString("destination"),
                    rs.getTimestamp("destination_time").toLocalDateTime(),
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
