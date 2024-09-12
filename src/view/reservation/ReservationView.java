package view.reservation;

import model.entities.Ticket;

import java.util.List;

public class ReservationView {
   
    public void searchJourney(List<List<Ticket>> paths) {
        if (paths.isEmpty()) {
            System.out.println("        No valid path found.");
        } else {
            System.out.println("        Found paths:");
            int journeyNumber = 1;
            for (List<Ticket> path : paths) {
                System.out.println("=====================================");
                System.out.println("        Journey " + journeyNumber + ":");
                System.out.println("=====================================");
                for (Ticket ticket : path) {
                    System.out.println("Departure: " + ticket.getDeparture() + " at " + ticket.getDepartureTime());
                    System.out.println("Destination: " + ticket.getDestination() + " at " + ticket.getDestinationTime());
                    System.out.println("Transport: " + ticket.getTransportType());
                    System.out.println("Purchase Price: " + ticket.getPurchasePrice());
                    System.out.println("Sale Price: " + ticket.getSalePrice());
                    System.out.println("Ticket Status: " + ticket.getTicketStatus());
                    System.out.println("-------------------------------------");
                }
                journeyNumber++;
                System.out.println();
            }
        }
    }
    
}
