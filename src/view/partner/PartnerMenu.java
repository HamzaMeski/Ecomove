package view.partner;

import controller.PartnerController;
import model.dao.PartnerDAO;
import view.partner.PartnerView;
import model.entities.Partner;
import model.enums.PartnerStatus;
import model.enums.TransportType;
import lib.ScanInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class PartnerMenu {
    PartnerDAO model = new PartnerDAO(); 
    PartnerView view = new PartnerView();
    PartnerController partnerController = new PartnerController(model, view);

    

    public static void displayMenu() {
        PartnerMenu partnerMenu = new PartnerMenu();
        byte option;
        do {
            System.out.println("");
            System.out.println("--------------------------------------------------------------");
            System.out.println("|                                                            |");
            System.out.println("|                       Partner Management:                  |");
            System.out.println("|                                                            |");
            System.out.println("--------------------------------------------------------------");
            System.out.println(" -1) Add Partner:");
            System.out.println(" -2) Update Partner:");
            System.out.println(" -3) Delete Partner:");
            System.out.println(" -4) Search a Partner:");
            System.out.println(" -5) List all Partners:");
            System.out.println(" -6) Back:");

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
        System.out.println("||||||||||||||||||| PARTNER SECTION |||||||||||||||||||");

        /*
            setting companyName 
        */ 
        System.out.print("    company name= ");
        String companyName = ScanInput.scanner.nextLine();

        /*
            setting commercialContact
        */ 
        System.out.print("\n    commercial contact= ");
        String commercialContact = ScanInput.scanner.nextLine();

        /*
            setting transportTypeValue
        */ 
        String transportTypeValue;
        byte transportTypeOption;
        do {
            System.out.print("\n    >>transport type (1.PLANE, 2.TRAIN, 3.BUS) ");
            System.out.print("\n        >set 1, 3 or 3 for an option= ");
            transportTypeOption = ScanInput.scanner.nextByte(); 
            ScanInput.scanner.nextLine();
        } while(!(transportTypeOption == 1) && !(transportTypeOption == 2) && !(transportTypeOption == 3));
        if(transportTypeOption == 1) transportTypeValue = "PLANE";
        else if(transportTypeOption == 2) transportTypeValue = "TRAIN"; 
        else transportTypeValue = "BUS";
        TransportType transportType = TransportType.valueOf(transportTypeValue);

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
            System.out.print(" \n   >>partner status (ACTIVE, INACTIVE, SUSPENDED)= ");
            System.out.print("\n        >set 1, 2 or 3 for an option= ");
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate creationDate = null;
        boolean checker;
        do {
            try {
                System.out.print("\n    >>creation date (dd/MM/yy) ex(01/02/2024)= ");
                String dateString = ScanInput.scanner.nextLine();
                creationDate = LocalDate.parse(dateString, formatter);
                checker = false;
            }catch(DateTimeParseException e) {
                System.out.print("\n        !! Invalid date pattern !!");
                checker = true;
            }
        }while(checker);
        
        Partner partner = new Partner(0, companyName, commercialContact, transportType, geographicalArea, specialConditions, partnerStatus, creationDate);
        partnerController.addPartner(partner);
    }

    public void updatePartner() {

    }

    public void deletePartner() {

    }

    public void searchPartner() {

    }

    public void displayPartner() {

    }

    public void listAllPartners() {

    }
}
