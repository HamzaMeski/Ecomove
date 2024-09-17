package model.dao;

import java.sql.*;
import java.util.*;
import model.DbConfig;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.entities.Ticket;
import model.enums.TicketStatus;

public class GraphDAO {
    private Map<String, Map<String, Ticket>> citiesNetwork = new HashMap<>();

    public GraphDAO() {
        loadGraphFromDatabase();
    }

    private void loadGraphFromDatabase() {
        String sql = "SELECT v1.departure, v2.destination, t.id as ticket_id, t.transport_type, " +
                     "t.purchase_price, t.sale_price, t.sale_date, t.status, t.departure_time, t.destination_time, t.contract_id " +
                     "FROM vertixe1 v1 " +
                     "JOIN vertixe2 v2 ON v1.id = v2.vertixe1_id " +
                     "JOIN Ticket t ON t.id = v2.ticket_id " +
                     "WHERE t.status = 'PENDING'";

        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
    
            while (rs.next()) {
                String departure = rs.getString("departure");
                String destination = rs.getString("destination");
                int ticketId = rs.getInt("ticket_id");
                String transportType = rs.getString("transport_type");
                float purchasePrice = rs.getFloat("purchase_price");
                float salePrice = rs.getFloat("sale_price");
                LocalDate saleDate = rs.getObject("sale_date", LocalDate.class);
                TicketStatus ticketStatus = TicketStatus.valueOf(rs.getString("status"));
                LocalDateTime departureTime = rs.getObject("departure_time", LocalDateTime.class);
                LocalDateTime destinationTime = rs.getObject("destination_time", LocalDateTime.class);
                int contractId = rs.getInt("contract_id");
    
                Ticket ticket = new Ticket(ticketId, transportType, purchasePrice, salePrice, saleDate, ticketStatus,
                                           departure, departureTime, destination, destinationTime, contractId);
    
                citiesNetwork.computeIfAbsent(departure, k -> new HashMap<>()).put(destination, ticket);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public List<List<Ticket>> findPaths(String departure, String destination) {
        List<List<Ticket>> result = new ArrayList<>();
        List<Ticket> path = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        dfs(departure, destination, visited, path, result, null);
        return result;
    }

    private void dfs(String current, String destination, Set<String> visited, List<Ticket> path, List<List<Ticket>> result, LocalDateTime lastDestinationTime) {
        visited.add(current);
    
        if (current.equals(destination)) {
            result.add(new ArrayList<>(path));
        } else {
            Map<String, Ticket> neighbors = citiesNetwork.getOrDefault(current, Collections.emptyMap());
            for (Map.Entry<String, Ticket> entry : neighbors.entrySet()) {
                String neighbor = entry.getKey();
                Ticket ticket = entry.getValue();
                LocalDateTime ticketDepartureTime = ticket.getDepartureTime();
                LocalDateTime ticketDestinationTime = ticket.getDestinationTime();

                if (!visited.contains(neighbor) &&
                    (lastDestinationTime == null || !ticketDepartureTime.isBefore(lastDestinationTime))) {
                    path.add(ticket);
                    dfs(neighbor, destination, visited, path, result, ticketDestinationTime);
                    path.remove(path.size() - 1);
                }
            }
        }

        visited.remove(current);
    }

    public List<List<Ticket>> searchJourney(String departure, String destination) {
        GraphDAO graphSearch = new GraphDAO();
        List<List<Ticket>> paths = graphSearch.findPaths(departure, destination);

        return paths;
    }

    public void reserveJourneyTickets(String departure, String destination, int clientId) {
        List<List<Ticket>> journeys = searchJourney(departure, destination);
    
        if (journeys.isEmpty()) {
            System.out.println("No valid journey available for reservation.");
            return;
        }
    
        List<Ticket> selectedJourney = journeys.get(0);
    
        String insertReservationSQL = "INSERT INTO reservation (ticket_id, client_id, reservation_time) VALUES (?, ?, ?)";
    
        String updateTicketStatusSQL = "UPDATE Ticket SET status = 'SOLD' WHERE id = ?";
    
        Connection conn = null; 
        try {
            conn = DbConfig.getConnection();
            conn.setAutoCommit(false);
    
            for (Ticket ticket : selectedJourney) {
                try (PreparedStatement pstmt = conn.prepareStatement(insertReservationSQL)) {
                    pstmt.setInt(1, ticket.getId());
                    pstmt.setInt(2, clientId);
                    pstmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                    pstmt.executeUpdate();
                }
    
                try (PreparedStatement pstmt = conn.prepareStatement(updateTicketStatusSQL)) {
                    pstmt.setInt(1, ticket.getId()); 
                    pstmt.executeUpdate();
                }
            }
    
            conn.commit(); 
            System.out.println("Journey reserved, tickets updated to 'SOLD', and stored in the reservation table successfully!");
    
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }
    
}
