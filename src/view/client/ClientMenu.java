package view.client;

import model.dao.ClientDAO;
import model.entities.Partner;
import model.enums.PartnerStatus;
import model.enums.TransportType;
import src.view.partner.PartnerMenu;
import view.client.ClientView;
import view.contract.ContractMenu;
import controller.ClientController;
import lib.ScanInput;

import model.entities.Client;

import java.util.List;
import java.util.ArrayList;

public class ClientMenu {
    ClientDAO model = new ClientDAO();
    ClientView view = new ClientView();
    ClientController clientController = new ClientController(model, view);

    public static void displayMenu() {
        ClientMenu clientMenu = new ClientMenu();
        byte option;
        do {
            System.out.println("\n");
            System.out.println("  ┌─────────────────────────────────────────────────────────────┐");
            System.out.println("  │                                                             │");
            System.out.println("  │                ■ Client Profile Management ■                │");
            System.out.println("  │                                                             │");
            System.out.println("  └─────────────────────────────────────────────────────────────┘");
            System.out.println("");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.println("     │  1 - Add Client                                       │");
            System.out.println("     │  2 - Update Client                                    │");
            System.out.println("     │  3 - Delete Client                                    │");
            System.out.println("     │  4 - Search a Client                                  │");
            System.out.println("     │  5 - List all Clients                                 │");
            System.out.println("     │  6 - Back                                             │");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.print("\n     Enter your choice (1-6): ");

            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            String noClientMsg = "   There is no client for the momement!";
            switch (option) {
                case 1:
                    clientMenu.addClient();
                break;
    
                case 2:
                    if(clientMenu.isClientsNotEmpty()) clientMenu.updateClient();
                    else System.out.println(noClientMsg);
                break;
    
                case 3:
                    if(clientMenu.isClientsNotEmpty()) clientMenu.deleteClient();
                    else System.out.println(noClientMsg);
                break;
    
                case 4:
                    if(clientMenu.isClientsNotEmpty())  clientMenu.searchClient();
                    else System.out.println(noClientMsg);
                break;
    
                case 5:
                    if(clientMenu.isClientsNotEmpty())  clientMenu.listAllClients();
                    else System.out.println(noClientMsg);
                break;

            }
        }while (option != 6);
    }

    public boolean isClientsNotEmpty() {
        if(clientController.getClients().size() == 0) return false;
        else return true;
    }

    public void addClient() {
        System.out.println("********************* ADD Client *********************");

        System.out.print("      # first name: ");
        String firstName = ScanInput.scanner.nextLine();

        System.out.print("      # second name: ");
        String secondName = ScanInput.scanner.nextLine();

        System.out.print("      # phone number: ");
        String phoneNumber = ScanInput.scanner.nextLine();

        System.out.print("      # email: ");
        String email = ScanInput.scanner.nextLine();
        
        Client client = new Client(0, firstName, secondName, phoneNumber, email);
        clientController.addClient(client);
    }

    int getClientId() {
        boolean idExists;
        int enteredClientId;
        do {
            System.out.print("      Set the ID of the Client that you want to update: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredClientId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = clientController.getClients().stream().anyMatch(client -> client.getId() == enteredId);
             
            if (!idExists) {
                System.out.println("        The entered ID does not exist. Please try again!");
            }
        } while (!idExists);

        return enteredClientId;
    }

    void updateClient() {
        System.out.println("********************* UPDATE Client *********************");
        
        int clientId = getClientId();
        
        System.out.println("        <>Rows data to update:");
        System.out.println("        (1)>>first name. ");
        System.out.println("        (2)>>second name. ");
        System.out.println("        (3)>>phone number. ");
        System.out.println("        (4)>>email. ");
        System.out.println("        >>Done setting columns (5).");
        
        System.out.println("        <>Set an option that you want to update in the contract (1-5):");

        List<Integer> optionsSets = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        List<Integer> userOptions = new ArrayList<>();
        int option;
        do {
            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            if(!optionsSets.contains(option)) {
                System.out.print("\n        The value that you did set is not an option, set only the existing options: ");
            }
            if(!(option == 5) && optionsSets.contains(option)){
                userOptions.add(option);
            }
        }while(option != 5);

        String firstName = null;
        if(userOptions.contains(1)) {
            firstName = ScanInput.scanner.nextLine();
        }

        String secondName = null;
        if(userOptions.contains(2)) {
            secondName = ScanInput.scanner.nextLine();
        }

        String phoneNumber = null;
        if(userOptions.contains(3)) {
            phoneNumber = ScanInput.scanner.nextLine();
        }

        String email = null;
        if(userOptions.contains(4)) {
            email = ScanInput.scanner.nextLine();
        }

        Client client = new Client(0, firstName, secondName, phoneNumber, email);
        clientController.updateClient(client);
    }

    void deleteClient() {

    }

    void searchClient() {

    }
    
    void listAllClients() {
        clientController.listAllClients();
    }
}
