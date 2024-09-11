package model.dao;

import java.sql.*;
import java.util.*;
import model.DbConfig;
import java.time.LocalDate;
import java.time.LocalDateTime;

import model.entities.Ticket;
import model.enums.TransportType;
import model.enums.TicketStatus;

public class GraphSearch {
    private Map<String, Map<String, Ticket>> citiesNetwork = new HashMap<>();

    public GraphSearch() {
        loadGraphFromDatabase();
    }

    private void loadGraphFromDatabase() {
        String sql = "SELECT v1.departure, v2.destination, t.id as ticket_id, t.transport_type, " +
                     "t.purchase_price, t.sale_price, t.sale_date, t.status, t.departure_time, t.destination_time, t.contract_id " +
                     "FROM vertixe1 v1 " +
                     "JOIN vertixe2 v2 ON v1.id = v2.vertixe1_id " +
                     "JOIN Ticket t ON t.id = v2.ticket_id";
        
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                String departure = rs.getString("departure");
                String destination = rs.getString("destination");
                int ticketId = rs.getInt("ticket_id");
                TransportType transportType = TransportType.valueOf(rs.getString("transport_type"));
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

    public void getJourney(String departure, String destination) {
        GraphSearch graphSearch = new GraphSearch();
        List<List<Ticket>> paths = graphSearch.findPaths(departure, destination);

        if (paths.isEmpty()) {
            System.out.println("No valid path found.");
        } else {
            System.out.println("Found paths:");
            for (List<Ticket> path : paths) {
                System.out.println(path);
            }
        }
    }
}
