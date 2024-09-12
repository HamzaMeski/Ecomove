package view.reservation;

import model.dao.GraphDAO;
import controller.ReservationController;
import model.dao.TicketDAO;
import lib.ScanInput;

public class ReservationMenu {
    private GraphDAO model = new GraphDAO();
    private ReservationView view = new ReservationView();
    ReservationController reservationController = new ReservationController(model, view);
    
    public static void displayMenu() {
        ReservationMenu reservationMenu = new ReservationMenu();
        TicketDAO ticketDAO = new TicketDAO();
        if(ticketDAO.listAllTickets().size() == 0) {
            System.out.println("        There is no tickets currently in the system!");
            return;
        }

        byte option;
        do {
            System.out.println("\n");
            System.out.println("  ┌─────────────────────────────────────────────────────────────┐");
            System.out.println("  │                                                             │");
            System.out.println("  │               ■ Ticket Reservation section ■                │");
            System.out.println("  │                                                             │");
            System.out.println("  └─────────────────────────────────────────────────────────────┘");
            System.out.println("");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.println("     │  1 - Search journey                                   │");
            System.out.println("     │  2 - Reserve journey                                  │");
            System.out.println("     │  3 - Back                                             │");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.print("\n     Enter your choice (1-3): ");

            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            switch (option) {
                case 1:
                    reservationMenu.searchJourney();
                    break;
                case 2:
                    reservationMenu.reserveJourneyTickets();
                    break;
            }
        } while (option != 3);
    }
     
    void searchJourney() {
        System.out.println("        <> SEARCH DESTINATION <>");
        System.out.print("        <1> Set Departure to go from: ");
        String departure = ScanInput.scanner.nextLine();
        System.out.print("\n        <2> Set The Destination: ");
        String destination = ScanInput.scanner.nextLine();

        reservationController.searchJourney(departure, destination);
    }

    void reserveJourneyTickets() {
        System.out.println("        <> RESERVE JOURNEY TICKETS <>");
        System.out.print("        <1> Set Departure to go from: ");
        String departure = ScanInput.scanner.nextLine();
        System.out.print("\n        <2> Set The Destination: ");
        String destination = ScanInput.scanner.nextLine();

        reservationController.reserveJourneyTickets(departure, destination);
    }
}
