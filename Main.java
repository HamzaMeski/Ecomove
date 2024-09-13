import view.partner.PartnerMenu;
import view.contract.ContractMenu; 
import view.PromotionalOffer.PromotionalOfferMenu;
import view.ticket.TicketMenu;
import view.client.ClientMenu;
import view.reservation.ReservationMenu;
import controller.AuthenticationController;
import model.dao.ClientDAO;
import lib.ScanInput;

import java.util.List; 
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        Menus menus = new Menus();
        int option;
        do {
            System.out.println("    [1] |- Log-in as an admin  :");
            System.out.println("    [2] |- Sign-up as a client :");
            System.out.println("    [3] |- Log-in as a client  :");
            System.out.println("    [4] |- Exit program        :");
            System.out.print("        >>SET OPTION: ");
            
            option = ScanInput.scanner.nextInt();
            ScanInput.scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.println("        LOG AS AN ADMIN:");
                    System.out.print("        #username: ");
                    String userName = ScanInput.scanner.nextLine();
                    System.out.print("\n        #password:");
                    String password = ScanInput.scanner.nextLine();

                    if(userName.equals("HM") && password.equals("HM")) menus.displayAdminMenu();
                    else System.out.println("       Wrong informations!");
                break;
            
                case 2:
                    menus.signUp();
                break;

                case 3:
                    System.out.println("        LOG AS AN ADMIN:");
                    System.out.print("        #username:");
                    userName = ScanInput.scanner.nextLine();
                    System.out.print("\n        #password:");
                    password = ScanInput.scanner.nextLine();

                    ClientDAO model = new ClientDAO();
                    AuthenticationController authenticationController = new AuthenticationController(model);
                    Integer clientID = authenticationController.checkAccountInfo(userName, password);

                    if(clientID != null) menus.displayClientMenu();
                    else System.out.println("       Wrong informations!");
                break;
            }
        }while(option != 4);
    }
}

class Menus {
    public void displayAdminMenu() {
        byte option;
        do { 
            System.out.println("\n\n");
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                  ║");
            System.out.println("║                     ADMINISTRATION SECTION                       ║");
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
            System.out.println("║    [6] × Exit that section                                       ║");
            System.out.println("║                                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            System.out.print("\n    >> Enter your choice (1-6): ");
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
            }
        }while(option != 6);
    }
        
    public void displayClientMenu() {
        byte option;
        do { 
            System.out.println("\n\n");
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    ╔═══════════════════╗                         ║");
            System.out.println("║                    ║     <<ECOMOVE>>   ║                         ║");
            System.out.println("║                    ╚═══════════════════╝                         ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════╣");
            System.out.println("║                                                                  ║");
            System.out.println("║    [1] ■ Reserve Tickets                                         ║");
            System.out.println("║    [2] × Exit that section                                       ║");
            System.out.println("║                                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            System.out.print("\n    >> Enter your choice (1-2): ");
            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();
            switch(option) {
                case 1:
                    PartnerMenu.displayMenu();
                break;
            }
        }while(option != 7);
    }

    public void signUp() {
        byte option;
        do { 
            System.out.println("\n\n");
            System.out.println("╔══════════════════════════════════════════════════════════════════╗");
            System.out.println("║                    ╔═══════════════════╗                         ║");
            System.out.println("║                    ║     <<ECOMOVE>>   ║                         ║");
            System.out.println("║                    ╚═══════════════════╝                         ║");
            System.out.println("╠══════════════════════════════════════════════════════════════════╣");
            System.out.println("║                                                                  ║");
            System.out.println("║    [1] ■ SIGN-UP                                                 ║");
            System.out.println("║    [2] × Exit that section                                       ║");
            System.out.println("║                                                                  ║");
            System.out.println("╚══════════════════════════════════════════════════════════════════╝");
            System.out.print("\n    >> Enter your choice (1-2): ");
            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();
            switch(option) {
                case 1:
                    PartnerMenu.displayMenu();
                break;
            }
        }while(option != 7);
    }
}