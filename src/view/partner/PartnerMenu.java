package view.partner;

import controller.PartnerController;
import model.dao.PartnerDAO;
import view.partner.PartnerView;
import model.entities.Partner;
import lib.ScanInput;
import model.enums.PartnerStatus;
import model.enums.TransportType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class PartnerMenu {

    public static void displayMenu() {
        PartnerDAO model = new PartnerDAO(); 
        PartnerView view = new PartnerView();
        PartnerController partnerController = new PartnerController(model, view);

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
                    // setting companyName
                    System.out.print("    company name= ");
                    String companyName = ScanInput.scanner.nextLine();

                    // setting commercialContact
                    System.out.print("\n    commercial contact= ");
                    String commercialContact = ScanInput.scanner.nextLine();

                    // setting transportTypeValue
                    String transportTypeValue;
                    boolean checker = true;
                    do {
                        System.out.print("\n    transport type (PLANE, TRAIN, BUS)= ");
                        transportTypeValue = ScanInput.scanner.nextLine();
                        if(transportTypeValue.toUpperCase().equals("PLANE")) checker = false;
                        else if(transportTypeValue.toUpperCase().equals("TRAIN")) checker = false;
                        else if (transportTypeValue.toUpperCase().equals("BUS")) checker = false;
                        else System.out.println("Invalid Transport type plz use PLANE, TRAIN or BUS!");
                    } while(checker);
                    TransportType transportType = TransportType.valueOf(transportTypeValue);

                    // setting geographicalArea
                    System.out.print("\n    geographical area= ");
                    String geographicalArea = ScanInput.scanner.nextLine();

                    // setting specialConditions
                    System.out.print("\n    special conditions= ");
                    String specialConditions = ScanInput.scanner.nextLine();

                    // setting partnerStatusValue
                    String partnerStatusValue;
                    checker = true;
                    do {
                        System.out.print("\n    partner status= ");
                        partnerStatusValue = ScanInput.scanner.nextLine();
                        if(partnerStatusValue.toUpperCase().equals("ACTIVE")) checker = false;
                        else if(partnerStatusValue.toUpperCase().equals("INACTIVE")) checker = false;
                        else if (partnerStatusValue.toUpperCase().equals("SUSPENDED")) checker = false;
                        else System.out.println("Invalid partner status plz use PLANE, TRAIN or BUS!");
                    }while(checker);
                    PartnerStatus partnerStatus = PartnerStatus.valueOf(partnerStatusValue);

                    // setting creationDate
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
                    LocalDate creationDate = null;
                    // LocalDate creationDate;
                    checker = false;
                    do {
                        try {
                            System.out.print("\n    creation date (dd/MM/yy)= ");
                            String dateString = ScanInput.scanner.nextLine();
                            creationDate = LocalDate.parse(dateString, formatter);
                        }catch(DateTimeParseException e) {
                            checker = true;
                            System.out.println("Invalid date pattern!");
                        }
                    }while(checker);
                    
                    Partner partner = new Partner(0, companyName, commercialContact, transportType, geographicalArea, specialConditions, partnerStatus, creationDate);
                break;
    
                case 2:
                break;
    
                case 3:
                break;
    
                case 4:
                break;
    
                case 5:
                break;
    
            }
        }while (option != 6);
    }
}
