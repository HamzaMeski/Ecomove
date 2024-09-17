package view.partner;

import controller.PartnerController;
import model.dao.PartnerDAO;
import model.entities.Partner;
import model.enums.PartnerStatus;
import view.contract.ContractMenu;
import lib.ScanInput;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;


public class PartnerMenu {
    PartnerDAO model = new PartnerDAO(); 
    PartnerView view = new PartnerView();
    PartnerController partnerController = new PartnerController(model, view);

    public static void displayMenu() {
        PartnerMenu partnerMenu = new PartnerMenu();
        byte option;
        do {
            System.out.println("\n");
            System.out.println("  ┌─────────────────────────────────────────────────────────────┐");
            System.out.println("  │                                                             │");
            System.out.println("  │                   ■ Partner Management ■                    │");
            System.out.println("  │                                                             │");
            System.out.println("  └─────────────────────────────────────────────────────────────┘");
            System.out.println("");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.println("     │  1 - Add Partner                                      │");
            System.out.println("     │  2 - Update Partner                                   │");
            System.out.println("     │  3 - Delete Partner                                   │");
            System.out.println("     │  4 - Search a Partner                                 │");
            System.out.println("     │  5 - List all Partners                                │");
            System.out.println("     │  6 - Back                                             │");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.print("\n     Enter your choice (1-6): ");

            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            switch (option) {
                case 1:
                    partnerMenu.addPartner();
                break;
    
                case 2:
                    partnerMenu.updatePartner();
                break;
    
                case 3:
                    partnerMenu.deletePartner();
                break;
    
                case 4:
                    partnerMenu.searchPartner();
                break;
    
                case 5:
                    partnerMenu.listAllPartners();
                break;
    
            }
        }while (option != 6);
    }

    public void addPartner() {
        System.out.println("||||||||||||||||||| ADD PARTNER |||||||||||||||||||");

        /*
            setting companyName 
        */ 
        System.out.print("    >>company name= ");
        String companyName = ScanInput.scanner.nextLine();

        /*
            setting commercialContact
        */ 
        System.out.print("\n    >>commercial contact= ");
        String commercialContact = ScanInput.scanner.nextLine();

        /*
            setting transportTypeValue
        */
        System.out.print("\n    >>transport type ex(PLANE, TRAIN, BUS ...) ");
        String transportType = ScanInput.scanner.nextLine();

        /*
            setting geographicalArea 
        */ 
        System.out.print("\n    >>geographical area= ");
        String geographicalArea = ScanInput.scanner.nextLine();

        /*
            setting specialConditions
        */ 
        System.out.print("\n    >>special conditions= ");
        String specialConditions = ScanInput.scanner.nextLine();

        /*
            setting partnerStatusValue
        */ 
        String partnerStatusValue;
        byte partnerStatusOption;
        do {
            System.out.print(" \n   >>partner status (1.ACTIVE, 2.INACTIVE, 3.SUSPENDED)= ");
            System.out.print("\n        <>set 1, 2 or 3 for an option= ");
            partnerStatusOption = ScanInput.scanner.nextByte(); 
            ScanInput.scanner.nextLine();
            
        }while(!(partnerStatusOption == 1) && !(partnerStatusOption == 2) && !(partnerStatusOption == 3) );
        if(partnerStatusOption == 1) partnerStatusValue = "ACTIVE"; 
        else if (partnerStatusOption == 2) partnerStatusValue = "INACTIVE";
        else partnerStatusValue = "SUSPENDED";
        PartnerStatus partnerStatus = PartnerStatus.valueOf(partnerStatusValue);
        
        /*
            setting creationDate 
        */ 
        LocalDate creationDate = LocalDate.now();
        
        Partner partner = new Partner(0, companyName, commercialContact, transportType, geographicalArea, specialConditions, partnerStatus, creationDate);
        int insertedPartnerId = partnerController.addPartner(partner);

        // the partner when creating it at least it must have one contract with us as a plateform
        ContractMenu contractMenu = new ContractMenu();
        contractMenu.addContract(insertedPartnerId);
    }

    public void updatePartner() {
        System.out.println("||||||||||||||||||| UPDATE PARTNER |||||||||||||||||||");
    
        System.out.println();
        List<Partner> partners = partnerController.listAllPartners();
        if(partners.size() != 0) {

            System.out.print("    # Set the ID of the partner that you want to update: ");
            int partnerId =  ScanInput.scanner.nextInt();
            ScanInput.scanner.nextLine();
    
            /*
                Getting the column that user will update
            */ 
            System.out.println("        <>Rows data to update:");
            System.out.println("        (1)>>company name. ");
            System.out.println("        (2)>>commercial contact. ");
            System.out.println("        (3)>>transport type. ");
            System.out.println("        (4)>>geographical area. ");
            System.out.println("        (5)>>special conditions. ");
            System.out.println("        (6)>>partner status. ");
            System.out.println("        >>Done setting columns (7).");
            
            System.out.println("        <>Set an option that you want to update options between(1-7):");
    
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
    
            String companyName = null;
            if(userOptions.contains(1)) {
                System.out.print("      *Set the company name: ");
                companyName = ScanInput.scanner.nextLine();
            }

            String commercialContact = null;
            if(userOptions.contains(2)){
                System.out.print("\n        *Set the commercial contact: ");
                commercialContact = ScanInput.scanner.nextLine();
            } 

            String transportType = null;
            if(userOptions.contains(3)){
                System.out.print("\n        *transport type (PLANE, TRAIN, BUS ...) ");
                transportType = ScanInput.scanner.nextLine();
            }
            
            String geographicalArea = null;
            if(userOptions.contains(4)){
                System.out.print("\n        *Set the geographical area: ");
                geographicalArea = ScanInput.scanner.nextLine();
            }
            
            String specialConditions = null;
            if(userOptions.contains(5)){
                System.out.print("\n        *Set the special conditions: ");
                specialConditions = ScanInput.scanner.nextLine();
            }
    
            PartnerStatus partnerStatus = null;
            if(userOptions.contains(6)){
                String partnerStatusValue;
                byte partnerStatusOption;
                do {
                    System.out.print(" \n       *partner status (1.ACTIVE, 2.INACTIVE, 3.SUSPENDED): ");
                    System.out.print("\n            <>set 1, 2 or 3 for an option: ");
                    partnerStatusOption = ScanInput.scanner.nextByte(); 
                    ScanInput.scanner.nextLine();
                    
                }while(!(partnerStatusOption == 1) && !(partnerStatusOption == 2) && !(partnerStatusOption == 3) );
                if(partnerStatusOption == 1) partnerStatusValue = "ACTIVE"; 
                else if (partnerStatusOption == 2) partnerStatusValue = "INACTIVE";
                else partnerStatusValue = "SUSPENDED";
                partnerStatus = PartnerStatus.valueOf(partnerStatusValue);
            }
    
            /*
                Partner entity
            */ 
            Partner partner = new Partner
                (
                    partnerId, 
                    (companyName != null && !companyName.isEmpty())  ? companyName : null, 
                    (commercialContact != null && !commercialContact.isEmpty()) ? commercialContact : null,
                    (transportType != null) ? transportType: null,
                    (geographicalArea != null && !geographicalArea.isEmpty()) ? geographicalArea: null,
                    (specialConditions != null && !specialConditions.isBlank()) ? specialConditions: null,
                    (partnerStatus != null) ? partnerStatus : null,
                    null
                );
    
            partnerController.updatePartner(partner);
        }
    }

    public void deletePartner() {
        System.out.println("||||||||||||||||||| DELETE PARTNER |||||||||||||||||||");

        System.out.println();
        partnerController.listAllPartners();
        System.out.print("    # Set the ID of the partner that you want to delete: ");
        int partnerId =  ScanInput.scanner.nextInt();
        ScanInput.scanner.nextLine();
        partnerController.deletePartner(partnerId);
    }
    
    public void searchPartner() {
        System.out.println("||||||||||||||||||| DISPLAY PARTNER |||||||||||||||||||");
        System.out.print("    # Search the partner by name: ");
        String partnerName = ScanInput.scanner.nextLine();
        partnerController.searchPartner(partnerName);
    }

    public void listAllPartners() {
        partnerController.listAllPartners();
    }
}
