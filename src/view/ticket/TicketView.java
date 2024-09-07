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
    
    public List<Ticket> listAllTickets(List<Ticket> tickets) {
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
                    System.out.println("++++++++++++++++++++++++ TICKET ++++++++++++++++++++++++");
                    System.out.println("        >> id: " + ticket.getId());
                    System.out.println("        >> transport type: " + ticket.getTransportType());
                    System.out.println("        >> purchase price: " + ticket.getPurchasePrice());
                    System.out.println("        >> sale price: " + ticket.getSalePrice());
                    System.out.println("        >> sale date: " + ticket.getSaleDate());
                    System.out.println("        >> ticket status: " + ticket.getTicketStatus());
                    System.out.println("        >> contract id: " + ticket.getContractId());
                    System.out.println("");
                }
            }
        } else {
            System.out.println("        There are no assigned tickets to that contract!");
        }
        
        return currentContractTickets;
    }
} 