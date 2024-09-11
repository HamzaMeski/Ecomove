package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.DbConfig;
import model.entities.Ticket;
import model.enums.TransportType;
import model.enums.TicketStatus;

import java.sql.*;

public class TicketDAO {
    public void addTicket(Ticket ticket) {
        String ticketSql = "INSERT INTO Ticket (transport_type, purchase_price, sale_price, sale_date, status, departure, departure_time, destination, destination_time, contract_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
        String vertixe1Sql = "INSERT INTO vertixe1 (departure, departure_time) VALUES (?, ?) RETURNING id";
    
        String vertixe2Sql = "INSERT INTO vertixe2 (vertixe1_id, destination, destination_time, ticket_id) VALUES (?, ?, ?, ?)";
    
        try (
            Connection conn = DbConfig.getConnection();
            PreparedStatement ticketStmt = conn.prepareStatement(ticketSql, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement vertixe1Stmt = conn.prepareStatement(vertixe1Sql);
            PreparedStatement vertixe2Stmt = conn.prepareStatement(vertixe2Sql)
        ) {
            vertixe1Stmt.setString(1, ticket.getDeparture());
            vertixe1Stmt.setTimestamp(2, Timestamp.valueOf(ticket.getDepartureTime()));
            ResultSet vertixe1Rs = vertixe1Stmt.executeQuery();
    
            int vertixe1Id = 0;
            if (vertixe1Rs.next()) {
                vertixe1Id = vertixe1Rs.getInt(1); 
            }
    
            ticketStmt.setString(1, ticket.getTransportType().toString().toUpperCase());
            ticketStmt.setFloat(2, ticket.getPurchasePrice());
            ticketStmt.setFloat(3, ticket.getSalePrice());
            ticketStmt.setDate(4, null);
            ticketStmt.setString(5, ticket.getTicketStatus().toString().toUpperCase());
            ticketStmt.setString(6, ticket.getDeparture());
            ticketStmt.setTimestamp(7, Timestamp.valueOf(ticket.getDepartureTime()));
            ticketStmt.setString(8, ticket.getDestination());
            ticketStmt.setTimestamp(9, Timestamp.valueOf(ticket.getDestinationTime()));
            ticketStmt.setInt(10, ticket.getContractId());
    
            ticketStmt.executeUpdate();
            ResultSet ticketRs = ticketStmt.getGeneratedKeys();
    
            int ticketId = 0;
            if (ticketRs.next()) {
                ticketId = ticketRs.getInt(1); 
            }
    
            vertixe2Stmt.setInt(1, vertixe1Id); 
            vertixe2Stmt.setString(2, ticket.getDestination());
            vertixe2Stmt.setTimestamp(3, Timestamp.valueOf(ticket.getDestinationTime()));
            vertixe2Stmt.setInt(4, ticketId); 
    
            vertixe2Stmt.executeUpdate();
    
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
