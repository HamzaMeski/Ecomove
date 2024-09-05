package view.PromotionalOffer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import controller.PartnerController;
import controller.PromotionalOfferController;
import lib.ScanInput;
import model.dao.PartnerDAO;
import model.dao.PromotionalOfferDAO;
import model.entities.Contract;
import model.entities.Partner;
import view.PromotionalOffer.PromotionalOfferView;
import view.contract.ContractMenu;
import view.partner.PartnerView; 
import model.entities.PromotionalOffer;
import model.enums.ContractStatus;
import model.enums.DiscountType;
import model.enums.OfferStatus;

import model.dao.ContractDAO;
import view.contract.ContractView;
import controller.ContractController;

public class PromotionalOfferMenu {
    PromotionalOfferDAO model = new PromotionalOfferDAO(); 
    PromotionalOfferView view = new PromotionalOfferView();
    PromotionalOfferController promotionalOfferController = new PromotionalOfferController(model, view);

    public static void displayMenu() {
        PromotionalOfferMenu promotionalOfferMenu = new PromotionalOfferMenu();
        byte option;
        do {
            System.out.println("");
            System.out.println("--------------------------------------------------------------");
            System.out.println("|                                                            |");
            System.out.println("|                Promotional Offer Management:               |");
            System.out.println("|                                                            |");
            System.out.println("--------------------------------------------------------------");
            System.out.println(" -1) Add Promotion:");
            System.out.println(" -2) Update Promotion:");
            System.out.println(" -3) List all Promotions:");
            System.out.println(" -4) Back:");

            option = ScanInput.scanner.nextByte();
            ScanInput.scanner.nextLine();

            switch (option) {
                case 1:
                    promotionalOfferMenu.addPromotionalOffer();
                break;
    
                case 2:
                    promotionalOfferMenu.updatePromotionalOffer();
                break;
    
                case 3:
                    promotionalOfferMenu.listAllPromotionalOffers();
                break;
            }
        }while (option != 4);
    }

    int getContractId() {
        ContractDAO model = new ContractDAO();
        ContractView view = new ContractView();
        ContractController contractController = new ContractController(model, view);
        List<Contract> contracts = contractController.listAllContracts();

        boolean idExists;
        int enteredContractId;
        do {
            System.out.print("      Set the id of the contract that you want assign it that promotional offer: ");
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
 

    void addPromotionalOffer() {
          System.out.println("||||||||||||||||||| ADD PROMOTIONAL OFFER |||||||||||||||||||");
        /*
            Assigning promotion to contract  
        */
        int enteredContractId = getContractId();;

        /*
            setting offerName
        */ 
        System.out.print("\n    >>Set offer name= ");
        String offerName = ScanInput.scanner.nextLine();
      
        /*
            setting description
        */ 
        System.out.print("\n    >>Set description= ");
        String description = ScanInput.scanner.nextLine();

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
            setting discountType
        */ 
        String discountTypeValue = null;
        byte discountTypeOption;
        do {
            System.out.print(" \n   >>discount type (1.PERCENTAGE, 2.FIXED_AMOUNT)= ");
            System.out.print("\n        <>set 1, 2 for an option= ");
            discountTypeOption = ScanInput.scanner.nextByte(); 
            ScanInput.scanner.nextLine();

        }while(!(discountTypeOption == 1) && !(discountTypeOption == 2));
        if(discountTypeOption == 1) discountTypeValue = "PERCENTAGE"; 
        else if (discountTypeOption == 2) discountTypeValue = "FIXED_AMOUNT";
        DiscountType discountType = DiscountType.valueOf(discountTypeValue);

        /*
            setting discountValue
        */ 
        System.out.print("\n    >>Set discount value= ");
        Float discountValue = ScanInput.scanner.nextFloat();
        ScanInput.scanner.nextLine();


        /*
            setting conditions
        */ 
        System.out.print("\n    >>Set conditions= ");
        String conditions = ScanInput.scanner.nextLine();

        /*
            setting offerStatus
        */ 
        String offerStatusValue;
        byte offerStatusOption;
        do {
            System.out.print(" \n   >>offer status (1.ACTIVE, 2.EXPIRED, 3.SUSPENDED)= ");
            System.out.print("\n        <>set 1, 2 or 3 for an option= ");
            offerStatusOption = ScanInput.scanner.nextByte(); 
            ScanInput.scanner.nextLine();

        }while(!(offerStatusOption == 1) && !(offerStatusOption == 2) && !(offerStatusOption == 2));
        if(offerStatusOption == 1) offerStatusValue = "ACTIVE"; 
        else if (offerStatusOption == 2) offerStatusValue = "EXPIRED";
        else offerStatusValue = "SUSPENDED";
        OfferStatus offerStatus = OfferStatus.valueOf(offerStatusValue);


        PromotionalOffer promotionalOffer = new PromotionalOffer(0, offerName, description, startDate, endDate, discountType, discountValue, conditions, offerStatus, enteredContractId);
        promotionalOfferController.addPromotionalOffer(promotionalOffer);
    }

    void updatePromotionalOffer() {

    }

    void listAllPromotionalOffers() {
        promotionalOfferController.listAllPromotionalOffers();
    }
}
