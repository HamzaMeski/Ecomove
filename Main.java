import view.partner.PartnerMenu;
import view.contract.ContractMenu; 
import view.PromotionalOffer.PromotionalOfferMenu;
import view.ticket.TicketMenu;
import view.client.ClientMenu;
import view.reservation.ReservationMenu;

import lib.ScanInput;

public class Main {
    public static void main(String[] args) {
        /*
         * Application Menu:
         */
        byte option;
        do { 
            System.out.println("\n\n");
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                  ║");
            System.out.println("║                    ╔═══════════════════╗                         ║");
            System.out.println("║                    ║     <<ECOMOVE>>   ║                         ║");
            System.out.println("║                    ╚═══════════════════╝                         ║");
            System.out.println("║                  TRAVELS TICKETING SYSTEM                        ║");
            System.out.println("║                                                                  ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════╣");
            System.out.println("║                                                                  ║");
            System.out.println("║  Choose an option:                                               ║");
            System.out.println("║                                                                  ║");
            System.out.println("║    [1] ■ Manage Partners Profiles                                ║");
            System.out.println("║    [2] ■ Manage Contracts                                        ║");
            System.out.println("║    [3] ■ Manage Promotional Offers                               ║");
            System.out.println("║    [4] ■ Manage Tickets                                          ║");
            System.out.println("║    [5] ■ Manage Clients                                          ║");
            System.out.println("║    [6] ■ Reserve Tickets                                         ║");
            System.out.println("║    [7] × Exit                                                    ║");
            System.out.println("║                                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            System.out.print("\n    >> Enter your choice (1-5): ");
            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();
            switch(option) {
                case 1:
                    PartnerMenu.displayMenu();
                break;

                case 2: 
                    ContractMenu.displayMenu();
                break; 

                case 3: 
                    PromotionalOfferMenu.displayMenu();
                break; 

                case 4: 
                    TicketMenu.displayMenu();
                break;

                case 5: 
                    ClientMenu.displayMenu();
                break;

                case 6: 
                    ReservationMenu.displayMenu();
                break;
            }
        }while(option != 7);
    }
}