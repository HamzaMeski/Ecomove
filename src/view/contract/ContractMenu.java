package view.contract;

import controller.ContractController;
import controller.PartnerController;
import model.dao.ContractDAO;
import model.entities.Contract;
import model.entities.Partner;
import model.enums.ContractStatus;
import lib.ScanInput;

import model.dao.PartnerDAO;
import view.partner.PartnerView;

import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList; 

public class ContractMenu {
    ContractDAO model = new ContractDAO();
    ContractView view = new ContractView();
    ContractController contractController = new ContractController(model, view);

    public static void displayMenu() {
        ContractMenu contractMenu = new ContractMenu();
        byte option;
        do {
            System.out.println("");
            System.out.println("--------------------------------------------------------------");
            System.out.println("|                                                            |");
            System.out.println("|                      Contract Management:                  |");
            System.out.println("|                                                            |");
            System.out.println("--------------------------------------------------------------");
            System.out.println(" -1) Add Contract:");
            System.out.println(" -2) Update Contract:");
            System.out.println(" -3) List all Contracts:");
            System.out.println(" -4) Back:");

            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            switch (option) {
                case 1:
                    contractMenu.addContract(0);
                break;
    
                case 2:
                    contractMenu.updateContract();
                break;
    
                case 3:
                    contractMenu.listAllContracts();
                break;
    
            }
        }while (option != 4);
    }

    int getPartnerId() {
        PartnerDAO model = new PartnerDAO();
        PartnerView view = new PartnerView();
        PartnerController partnerController = new PartnerController(model, view);
        List<Partner> partners = partnerController.listAllPartners();

        boolean idExists;
        int enteredPartnerId;
        do {
            System.out.print("      Set the id of the company that you want to assign that contract: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredPartnerId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = partners.stream().anyMatch(partner -> partner.getId() == enteredId);
             
            if (!idExists) {
                System.out.println("        The entered ID does not exist. Please try again!");
            }
        } while (!idExists);

        return enteredPartnerId;
    }
 
    public void addContract(int PartnerId) {
        System.out.println("||||||||||||||||||| ADD CONTRACT |||||||||||||||||||");
        /*
            Assigning contract to partner  
        */
        int enteredPartnerId = PartnerId;
        if(PartnerId == 0) {
            enteredPartnerId = getPartnerId();
        }

        /*
            setting startTime 
        */ 
        LocalDate startDate = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        boolean checker;
        do {
            try {
                System.out.print("\n    >>Set start date (dd/MM/yy) (ex: 01/02/2024)= ");
                String dateString = ScanInput.scanner.nextLine();
                startDate = LocalDate.parse(dateString, formatter);
                checker = false;
            }catch(DateTimeParseException e) {
                System.out.print("\n        !! Invalid date pattern !!");
                checker = true;
            }
        }while(checker);

        /*
            setting endTime 
        */ 
        LocalDate endDate = null;
        do {
            try {
                System.out.print("\n    >>Set end date (dd/MM/yy) (ex: 01/02/2024)= ");
                String dateString = ScanInput.scanner.nextLine();
                endDate = LocalDate.parse(dateString, formatter);
                checker = false;
            }catch(DateTimeParseException e) {
                System.out.print("\n        !! Invalid date pattern !!");
                checker = true;
            }
        }while(checker);
        
        /*
            setting specialPrice
        */ 
        System.out.print("\n    >>Set special price= ");
        float specialPrice = ScanInput.scanner.nextFloat();
        ScanInput.scanner.nextLine();

      
        /*
            setting agreementConditions
        */ 
        System.out.print("\n    >>Set agreement conditions= ");
        String agreementConditions = ScanInput.scanner.nextLine();

        /*
            setting renewable (false/true)
        */
        String option;
        do {
            System.out.println("\n  >>Is contract renewable(true/false)");
            option = ScanInput.scanner.nextLine().toLowerCase();
            
        }while(!option.equals("true") && !option.equals("false"));
        boolean renewable;
        if(option.equals("true")) renewable = true;
        else renewable = false;

        /*
            setting partnerStatusValue
        */ 
        String contractStatusValue;
        byte contractStatusOption;
        do {
            System.out.print(" \n   >>contract status (1.ONGOING, 2.COMPLETED, 3.SUSPENDED)= ");
            System.out.print("\n        <>set 1, 2 or 3 for an option= ");
            contractStatusOption = ScanInput.scanner.nextByte(); 
            ScanInput.scanner.nextLine();

        }while(!(contractStatusOption == 1) && !(contractStatusOption == 2) && !(contractStatusOption == 3) );
        if(contractStatusOption == 1) contractStatusValue = "ONGOING"; 
        else if (contractStatusOption == 2) contractStatusValue = "COMPLETED";
        else contractStatusValue = "SUSPENDED";
        ContractStatus contractStatus = ContractStatus.valueOf(contractStatusValue);

        Contract contract = new Contract(0, startDate, endDate, specialPrice, agreementConditions, renewable, contractStatus, enteredPartnerId);
        contractController.addContract(contract);
    }

    void updateContract() {
        System.out.println("||||||||||||||||||| UPDATE CONTRACT |||||||||||||||||||");

        System.out.println();
        
        List<Contract> contracts = contractController.listAllContracts();
        if(contracts.size() != 0) {
            boolean contractIdExist;
            int enteredContractId;
            do {
                System.out.print("      Set the ID of the contract that you want to update: ");
                int enteredId = ScanInput.scanner.nextInt();
                enteredContractId = enteredId;
                ScanInput.scanner.nextLine();
                contractIdExist = contracts.stream().anyMatch(contract -> contract.getId() == enteredId);
                
                if (!contractIdExist) {
                    System.out.println("        There is no matching Contract for that ID. Please try again!");
                }
            } while (!contractIdExist);
            
            /*
                Getting the column that user will update
            */ 
            System.out.println("        <>Rows data to update:");
            System.out.println("        (1)>>start date. ");
            System.out.println("        (2)>>end date. ");
            System.out.println("        (3)>>special price. ");
            System.out.println("        (4)>>agreement conditions. ");
            System.out.println("        (5)>>renewable. ");
            System.out.println("        (6)>>contract status. ");
            System.out.println("        >>Done setting columns (7).");
            
            System.out.println("        <>Set an option that you want to update in the contract (1-7):");

            List<Integer> optionsSets = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7));
            List<Integer> userOptions = new ArrayList<>();
            int option;
            do {
                option = ScanInput.scanner.nextByte();
                ScanInput.scanner.nextLine();

                if(!optionsSets.contains(option)) {
                    System.out.print("\n        The value that you did set is not an option, set only the existing options: ");
                }
                if(!(option == 7) && optionsSets.contains(option)){
                    userOptions.add(option);
                }
            }while(option != 7);
            //
            /*
                Assigning contract to partner  
            */

            /*
                setting startTime 
            */ 
            LocalDate startDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            boolean checker;
            if(userOptions.contains(1)) {
                do {
                    try {
                        System.out.print("\n    >>Set start date (dd/MM/yy) (ex: 01/02/2024)= ");
                        String dateString = ScanInput.scanner.nextLine();
                        startDate = LocalDate.parse(dateString, formatter);
                        checker = false;
                    }catch(DateTimeParseException e) {
                        System.out.print("\n        !! Invalid date pattern !!");
                        checker = true;
                    }
                }while(checker);
            }
            /*
                setting endTime 
            */ 
            LocalDate endDate = null;
            if(userOptions.contains(2)) {
                do {
                    try {
                        System.out.print("\n    >>Set end date (dd/MM/yy) (ex: 01/02/2024)= ");
                        String dateString = ScanInput.scanner.nextLine();
                        endDate = LocalDate.parse(dateString, formatter);
                        checker = false;
                    }catch(DateTimeParseException e) {
                        System.out.print("\n        !! Invalid date pattern !!");
                        checker = true;
                    }
                }while(checker);
            }
            
            /*
                setting specialPrice
            */ 
            Float specialPrice = null;
            if(userOptions.contains(3)) {
                System.out.print("\n    >>Set special price= ");
                specialPrice = ScanInput.scanner.nextFloat();
                ScanInput.scanner.nextLine();
            }

            /*
                setting agreementConditions
            */ 
            String agreementConditions = null;
            if(userOptions.contains(4)) {
                System.out.print("\n    >>Set agreement conditions= ");
                agreementConditions = ScanInput.scanner.nextLine();
            }

            /*
                setting renewable (false/true)
            */
            Boolean renewable = null;
            if(userOptions.contains(5)) {
                String boolOption;
                do {
                    System.out.println("\n   >>Is contract renewable(true/false)");
                    boolOption = ScanInput.scanner.nextLine().toLowerCase();
                    
                }while(!boolOption.equals("true") && !boolOption.equals("false"));
                if(boolOption.equals("true")) renewable = true;
                else renewable = false;
            }


            /*
                setting partnerStatusValue
            */ 
            
            ContractStatus contractStatus = null;
            if(userOptions.contains(6)) {
                String contractStatusValue;
                byte contractStatusOption;
                do {
                    System.out.print(" \n   >>contract status (1.ONGOING, 2.COMPLETED, 3.SUSPENDED)= ");
                    System.out.print("\n        <>set 1, 2 or 3 for an option= ");
                    contractStatusOption = ScanInput.scanner.nextByte(); 
                    ScanInput.scanner.nextLine();
                }while(!(contractStatusOption == 1) && !(contractStatusOption == 2) && !(contractStatusOption == 3));
                if(contractStatusOption == 1) contractStatusValue = "ONGOING"; 
                else if (contractStatusOption == 2) contractStatusValue = "COMPLETED";
                else contractStatusValue = "SUSPENDED";
                contractStatus = ContractStatus.valueOf(contractStatusValue);
            }

            Contract contract = new Contract(enteredContractId, startDate, endDate, specialPrice, agreementConditions, renewable, contractStatus, 0);
            contractController.updateContract(contract);
        }
    }

    void listAllContracts() {
        contractController.listAllContracts();
    }
}
