package view.PromotionalOffer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

import controller.PromotionalOfferController;
import lib.ScanInput;
import model.dao.PromotionalOfferDAO;
import model.entities.Contract;
import model.entities.PromotionalOffer;
import model.enums.DiscountType;
import model.enums.OfferStatus;

import model.dao.ContractDAO;
import view.contract.ContractView;
import controller.ContractController;

public class PromotionalOfferMenu {
    PromotionalOfferDAO model = new PromotionalOfferDAO(); 
    PromotionalOfferView view = new PromotionalOfferView();
    PromotionalOfferController promotionalOfferController = new PromotionalOfferController(model, view);

    ContractDAO contractModel = new ContractDAO();
    ContractView contractView = new ContractView();
    ContractController contractController = new ContractController(contractModel, contractView);
    List<Contract> partnerContracts = contractController.listAllContracts();

    public static void displayMenu() {
        PromotionalOfferMenu promotionalOfferMenu = new PromotionalOfferMenu();
        
        if(promotionalOfferMenu.partnerContracts.size() == 0) return;
        
        byte option;
        do {
            System.out.println("\n");
            System.out.println("  ┌─────────────────────────────────────────────────────────────┐");
            System.out.println("  │                                                             │");
            System.out.println("  │             ■ Promotional Offer Management ■                │");
            System.out.println("  │                                                             │");
            System.out.println("  └─────────────────────────────────────────────────────────────┘");
            System.out.println("");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.println("     │  1 - Add Promotion                                    │");
            System.out.println("     │  2 - Update Promotion                                 │");
            System.out.println("     │  3 - List all Promotions                              │");
            System.out.println("     │  4 - Back                                             │");
            System.out.println("     ─────────────────────────────────────────────────────────");
            System.out.print("\n     Enter your choice (1-4): ");

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
                    promotionalOfferMenu.listContractPromotionalOffers();
                break;
            }
        }while (option != 4);
    }

    int getContractId() {
        boolean idExists;
        int enteredContractId;
        do {
            System.out.print("      Set the ID of the contract that you want assign it to that promotional offer: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredContractId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = partnerContracts.stream().anyMatch(contract -> contract.getId() == enteredId);
            
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
        int enteredContractId = getContractId();

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
        System.out.println("||||||||||||||||||| UPDATE PROMOTIONAL OFFER |||||||||||||||||||");
        System.out.println();

        List<PromotionalOffer> promotionalOffers = promotionalOfferController.listContractPromotionalOffers();
        int contractId;
        if(promotionalOffers.size() != 0) {
            contractId = promotionalOffers.get(0).getContractId();
            boolean promotionalOfferIdExist;
            int enteredPromotionId;
            do {
                System.out.print("      Set the ID of the promotional offer that you want to update: ");
                int enteredId = ScanInput.scanner.nextInt();
                enteredPromotionId = enteredId;
                ScanInput.scanner.nextLine();
                promotionalOfferIdExist = promotionalOffers.stream().anyMatch(promotion -> promotion.getId() == enteredId);
    
                if (!promotionalOfferIdExist) {
                    System.out.println("        There is no matching Promotion for that ID. Please try again!");
                }
            } while (!promotionalOfferIdExist);

            /*
                Getting the column that user will update
            */ 
            System.out.println("        <>Rows data to update:");
            System.out.println("        (1)>>offer_name. ");
            System.out.println("        (2)>>description. ");
            System.out.println("        (3)>>start_date. ");
            System.out.println("        (4)>>end_date. ");
            System.out.println("        (5)>>discount_type. ");
            System.out.println("        (6)>>discount_value. ");
            System.out.println("        (7)>>conditions. ");
            System.out.println("        (8)>>status ('ACTIVE', 'EXPIRED', 'SUSPENDED') ");
            System.out.println("        >>Done setting columns (9).");
            
            System.out.println("        <>Set an option that you want to update in the promotion (1-9):");

            List<Integer> optionsSets = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
            List<Integer> userOptions = new ArrayList<>();
            int option;
            do {
                option = ScanInput.scanner.nextByte();
                ScanInput.scanner.nextLine();

                if(!optionsSets.contains(option)) {
                    System.out.print("\n        The value that you did set is not an option, set only the existing options: ");
                }
                if(!(option == 9) && optionsSets.contains(option)){
                    userOptions.add(option);
                }
            }while(option != 9);

            /*
                setting offerName
            */ 
            String offerName = null;
            if(userOptions.contains(1)) {
                System.out.print("\n    >>Set offer name= ");
                offerName = ScanInput.scanner.nextLine();
            }
          
            /*
                setting description
            */ 
            String description = null;
            if(userOptions.contains(2)) {
                System.out.print("\n    >>Set description= ");
                description = ScanInput.scanner.nextLine();
            }

            /*
                setting startTime 
            */ 
            LocalDate startDate = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            boolean checker;
            if(userOptions.contains(3)) {
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
            if(userOptions.contains(4)) {
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
                setting discountType
            */ 
            String discountTypeValue = null;
            DiscountType discountType = null;
            if(userOptions.contains(5)) {
                byte discountTypeOption;
                do {
                    System.out.print(" \n   >>discount type (1.PERCENTAGE, 2.FIXED_AMOUNT)= ");
                    System.out.print("\n        <>set 1, 2 for an option= ");
                    discountTypeOption = ScanInput.scanner.nextByte(); 
                    ScanInput.scanner.nextLine();
    
                }while(!(discountTypeOption == 1) && !(discountTypeOption == 2));
                if(discountTypeOption == 1) discountTypeValue = "PERCENTAGE"; 
                else if (discountTypeOption == 2) discountTypeValue = "FIXED_AMOUNT";
                discountType = DiscountType.valueOf(discountTypeValue);
            }

            /*
                setting discountValue
            */ 
            Float discountValue = null;
            if(userOptions.contains(6)) {
                System.out.print("\n    >>Set discount value= ");
                discountValue = ScanInput.scanner.nextFloat();
                ScanInput.scanner.nextLine();
            }

            /*
                setting conditions
            */ 
            String conditions = null;
            if(userOptions.contains(7)) {
                System.out.print("\n    >>Set conditions= ");
                conditions = ScanInput.scanner.nextLine();
            }

            /*
                setting offerStatus
            */ 
            String offerStatusValue;
            byte offerStatusOption;
            OfferStatus offerStatus = null;
            if(userOptions.contains(8)) {
                do {
                    System.out.print(" \n   >>offer status (1.ACTIVE, 2.EXPIRED, 3.SUSPENDED)= ");
                    System.out.print("\n        <>set 1, 2 or 3 for an option= ");
                    offerStatusOption = ScanInput.scanner.nextByte(); 
                    ScanInput.scanner.nextLine();
    
                }while(!(offerStatusOption == 1) && !(offerStatusOption == 2) && !(offerStatusOption == 2));
                if(offerStatusOption == 1) offerStatusValue = "ACTIVE"; 
                else if (offerStatusOption == 2) offerStatusValue = "EXPIRED";
                else offerStatusValue = "SUSPENDED";
                offerStatus = OfferStatus.valueOf(offerStatusValue);
            }

            PromotionalOffer promotionalOffer = new PromotionalOffer(enteredPromotionId, offerName, description, startDate, endDate, discountType, discountValue, conditions, offerStatus, contractId);
            System.out.println("#############");
            System.out.println(promotionalOffer);
            System.out.println("#############");
            promotionalOfferController.updatePromotionalOffer(promotionalOffer);
        }
    }

    void listContractPromotionalOffers() {
        promotionalOfferController.listContractPromotionalOffers();
    }
}
