package view.ticket;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

import controller.TicketController;
import lib.ScanInput;
import model.dao.TicketDAO;
import model.entities.Ticket;
import model.entities.Contract;
import model.enums.TransportType;
import model.enums.TicketStatus;

import model.dao.ContractDAO;
import view.contract.ContractView;
import controller.ContractController;

public class TicketMenu {
    TicketDAO model = new TicketDAO();
    TicketView view = new TicketView();
    TicketController ticketController = new TicketController(model, view);

    public static void displayMenu() {
        TicketMenu ticketMenu = new TicketMenu();
        byte option;
        do {
            System.out.println("");
            System.out.println("--------------------------------------------------------------");
            System.out.println("|                                                            |");
            System.out.println("|                    Ticket Management:                      |");
            System.out.println("|                                                            |");
            System.out.println("--------------------------------------------------------------");
            System.out.println(" -1) Add Ticket:");
            System.out.println(" -2) Update Ticket:");
            System.out.println(" -3) List all Tickets:");
            System.out.println(" -4) Back:");

            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            switch (option) {
                case 1:
                    ticketMenu.addTicket();
                    break;
                case 2:
                    ticketMenu.updateTicket();
                    break;
                case 3:
                    ticketMenu.listAllTickets();
                    break;
            }
        } while (option != 4);
    }

    int getContractId() {
        ContractDAO model = new ContractDAO();
        ContractView view = new ContractView();
        ContractController contractController = new ContractController(model, view);
        List<Contract> contracts = contractController.listAllContracts();

        boolean idExists;
        int enteredContractId;
        do {
            System.out.print("      Set the ID of the contract that you want to assign this ticket to: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredContractId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = contracts.stream().anyMatch(contract -> contract.getId() == enteredId);
            
            if (!idExists) {
                System.out.println("        The entered ID does not exist. Please try again!");
            }
        } while (!idExists);

        return enteredContractId;
    }

    void addTicket() {
        System.out.println("||||||||||||||||||| ADD TICKET |||||||||||||||||||");

        int enteredContractId = getContractId();

        TransportType transportType = getTransportType();

        System.out.print("\n    >>Set purchase price= ");
        Float purchasePrice = ScanInput.scanner.nextFloat();
        ScanInput.scanner.nextLine();

        System.out.print("\n    >>Set sale price= ");
        Float salePrice = ScanInput.scanner.nextFloat();
        ScanInput.scanner.nextLine();

        LocalDate saleDate = getSaleDate();

        TicketStatus ticketStatus = getTicketStatus();

        Ticket ticket = new Ticket(0, transportType, purchasePrice, salePrice, saleDate, ticketStatus, enteredContractId);
        ticketController.addTicket(ticket);
    }

    void updateTicket() {
        System.out.println("||||||||||||||||||| UPDATE TICKET |||||||||||||||||||");
        System.out.println();

        List<Ticket> tickets = ticketController.listAllTickets();
        if (tickets.size() != 0) {
            int ticketId = getTicketId(tickets);

            List<Integer> userOptions = getUserUpdateOptions();

            TransportType transportType = null;
            if (userOptions.contains(1)) {
                transportType = getTransportType();
            }

            Float purchasePrice = null;
            if (userOptions.contains(2)) {
                System.out.print("\n    >>Set purchase price= ");
                purchasePrice = ScanInput.scanner.nextFloat();
                ScanInput.scanner.nextLine();
            }

            Float salePrice = null;
            if (userOptions.contains(3)) {
                System.out.print("\n    >>Set sale price= ");
                salePrice = ScanInput.scanner.nextFloat();
                ScanInput.scanner.nextLine();
            }

            LocalDate saleDate = null;
            if (userOptions.contains(4)) {
                saleDate = getSaleDate();
            }

            TicketStatus ticketStatus = null;
            if (userOptions.contains(5)) {
                ticketStatus = getTicketStatus();
            }

            Ticket ticket = new Ticket(ticketId, transportType, purchasePrice, salePrice, saleDate, ticketStatus, 0);
            ticketController.updateTicket(ticket);
        }
    }

    private TransportType getTransportType() {
        String transportTypeValue;
        byte transportTypeOption;
        do {
            System.out.print(" \n   >>transport type (1.PLANE, 2.TRAIN, 3.BUS)= ");
            System.out.print("\n        <>set 1, 2 or 3 for an option= ");
            transportTypeOption = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();
        } while (!(transportTypeOption == 1) && !(transportTypeOption == 2) && !(transportTypeOption == 3));
        
        if (transportTypeOption == 1) transportTypeValue = "PLANE";
        else if (transportTypeOption == 2) transportTypeValue = "TRAIN";
        else transportTypeValue = "BUS";
        return TransportType.valueOf(transportTypeValue);
    }

    private LocalDate getSaleDate() {
        LocalDate saleDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean checker;
        do {
            try {
                System.out.print("\n    >>Set sale date (dd/MM/yyyy) (ex: 01/02/2024)= ");
                String dateString = ScanInput.scanner.nextLine();
                saleDate = LocalDate.parse(dateString, formatter);
                checker = false;
            } catch (DateTimeParseException e) {
                System.out.print("\n        !! Invalid date pattern !!");
                checker = true;
            }
        } while (checker);
        return saleDate;
    }

    private TicketStatus getTicketStatus() {
        String ticketStatusValue;
        byte ticketStatusOption;
        do {
            System.out.print(" \n   >>ticket status (1.SOLD, 2.CANCELED, 3.PENDING)= ");
            System.out.print("\n        <>set 1, 2 or 3 for an option= ");
            ticketStatusOption = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();
        } while (!(ticketStatusOption == 1) && !(ticketStatusOption == 2) && !(ticketStatusOption == 3));
        
        if (ticketStatusOption == 1) ticketStatusValue = "SOLD";
        else if (ticketStatusOption == 2) ticketStatusValue = "CANCELED";
        else ticketStatusValue = "PENDING";
        return TicketStatus.valueOf(ticketStatusValue);
    }

    private int getTicketId(List<Ticket> tickets) {
        boolean ticketIdExist;
        int enteredTicketId;
        do {
            System.out.print("      Set the ID of the ticket that you want to update: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredTicketId = enteredId;
            ScanInput.scanner.nextLine();
            ticketIdExist = tickets.stream().anyMatch(ticket -> ticket.getId() == enteredId);

            if (!ticketIdExist) {
                System.out.println("        There is no matching Ticket for that ID. Please try again!");
            }
        } while (!ticketIdExist);
        return enteredTicketId;
    }

    private List<Integer> getUserUpdateOptions() {
        System.out.println("        <>Rows data to update:");
        System.out.println("        (1)>>transport_type. ");
        System.out.println("        (2)>>purchase_price. ");
        System.out.println("        (3)>>sale_price. ");
        System.out.println("        (4)>>sale_date. ");
        System.out.println("        (5)>>status ('SOLD', 'CANCELED', 'PENDING') ");
        System.out.println("        >>Done setting columns (6).");
        
        System.out.println("        <>Set an option that you want to update in the ticket (1-6):");

        List<Integer> optionsSets = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6));
        List<Integer> userOptions = new ArrayList<>();
        int option;
        do {
            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            if (!optionsSets.contains(option)) {
                System.out.print("\n        The value that you did set is not an option, set only the existing options: ");
            }
            if (!(option == 6) && optionsSets.contains(option)) {
                userOptions.add(option);
            }
        } while (option != 6);

        return userOptions;
    }

    void listAllTickets() {
        ticketController.listAllTickets();
    }
}