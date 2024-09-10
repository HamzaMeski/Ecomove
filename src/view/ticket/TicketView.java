package view.ticket;

import java.util.ArrayList;
import java.util.List;

import controller.ContractController;
import lib.ScanInput;
import model.entities.Ticket;
import model.dao.ContractDAO;
import model.entities.Contract;
import view.contract.ContractView;

public class TicketView {

    public void displayAddTicketMessage() {
        System.out.println("    Ticket added successfully!");
    }
     
    public void displayUpdateTicketMessage() {
        System.out.println("    Ticket updated successfully!");
    }
    
    public List<Ticket> listContractTickets(List<Ticket> tickets) {
        System.out.println();

        ContractDAO model = new ContractDAO();
        ContractView view = new ContractView();
        ContractController contractController = new ContractController(model, view);
        List<Contract> contracts = contractController.listAllContracts();

        boolean idExists;
        int enteredContractId;
        do {
            System.out.print("      Set the ID of the contract that you want to showcase its tickets: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredContractId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = contracts.stream().anyMatch(contract -> contract.getId() == enteredId);
            
            if (!idExists) {
                System.out.println("        The entered ID does not exist. Please try again!");
            }
        } while (!idExists);

        List<Ticket> currentContractTickets = new ArrayList<>();

        int contractIdFinal = enteredContractId;
        boolean isTicketsNotEmpty = tickets.stream().anyMatch(ticket -> ticket.getContractId() == contractIdFinal);
        if (isTicketsNotEmpty) {
            for (Ticket ticket : tickets) {
                if (ticket.getContractId() == enteredContractId) {
                    currentContractTickets.add(ticket);
                    System.out.print("\n════════════════ TICKET ID: " + ticket.getId()+" ═════════════════");
                    System.out.print("\n  * Transport Type: "+      ticket.getTransportType());
                    System.out.print("\n  * Purchase Price: "+      ticket.getPurchasePrice());
                    System.out.print("\n  * Sale Price    : "+      ticket.getSalePrice());
                    System.out.print("\n  * Sale Date     : "+      ticket.getSaleDate());
                    System.out.print("\n  * Ticket Status : "+      ticket.getTicketStatus());
                    System.out.print("\n  * Ticket Status : "+      ticket.getTicketStatus());
                    System.out.print("\n  * Ticket departure : "+      ticket.getDeparture());
                    System.out.print("\n  * Ticket depatrure time : "+      ticket.getDepartureTime());
                    System.out.print("\n  * Ticket destination : "+      ticket.getDestination());
                    System.out.print("\n  * Ticket destination time : "+      ticket.getDestinationTime());
                    System.out.print("\n  * Contract ID   : "+      ticket.getContractId());
                    System.out.println("\n════════════════════════════════════════════════════════════════");
                    System.out.println();
                }
            }
        } else {
            System.out.println("        There are no assigned tickets to that contract!");
        }
        
        return currentContractTickets;
    }
} 