import view.partner.PartnerMenu;
import view.contract.ContractMenu; 
import view.PromotionalOffer.PromotionalOfferMenu;
import view.ticket.TicketMenu;

import lib.ScanInput;

public class Main {
    public static void main(String[] args) {

        byte option;
        do {
            System.out.println("--------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("|                                                                  |");
            System.out.println("|                           <<ECOMOVE>>                            |");
            System.out.println("|             TRAVELS TICKETING SYSTEM ADMINISTRATION              |");
            System.out.println("|                                                                  |");
            System.out.println("|                                                                  |");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("--------------------------------------------------------------------");
            System.out.println("Choose an option that you want to handle from the following:");
            System.out.println("    1=> Manage Partners Profiles:");
            System.out.println("    2=> Manage Contracts:");
            System.out.println("    3=> Manage Promotional Offers:");
            System.out.println("    4=> Manage Tickets:");
            System.out.println("    5=> Exit:");
            System.out.print("    >>Set an option: ");
            
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
            }
        }while(option != 5);
    }
} 