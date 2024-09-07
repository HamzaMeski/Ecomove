package view.PromotionalOffer;

import java.util.List;
import lib.ScanInput;

import java.util.ArrayList;
import model.entities.PromotionalOffer;
import model.entities.Contract;      
import controller.ContractController;
import model.dao.ContractDAO;        
import view.contract.ContractView;   

public class PromotionalOfferView {
    public void showAddPromotionMessage() {
        System.out.println("    Promotional offer added successfully!");
    }

    public void showUpdatePromotionMessage() {
        System.out.println("    Promotional offer updated successfully!");
    }

    public List<PromotionalOffer> listContractPromotionalOffers(List<PromotionalOffer> promotionalOffers) {
        System.out.println();
        ContractDAO model = new ContractDAO();
        ContractView view = new ContractView();
        ContractController contractController = new ContractController(model, view);
        List<Contract> contracts = contractController.listAllContracts();
        
        boolean idExists;
        int enteredContractId;
        do {
            System.out.print("      Set the ID of the contract that you want to showcase its promotions: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredContractId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = contracts.stream().anyMatch(contract -> contract.getId() == enteredId);
            
            if (!idExists) {
                System.out.println("        The entered ID does not exist. Please try again!");
            }
        } while (!idExists);

        // 
        List<PromotionalOffer> currentContractPromotionalOffers = new ArrayList(List.of());

        int contractIdFinal = enteredContractId;
        boolean isPromotionalOffersNotEmpty = promotionalOffers.stream().anyMatch( promotion -> promotion.getContractId() == contractIdFinal );
        if(isPromotionalOffersNotEmpty) {
            for(byte i = 0; i < promotionalOffers.size(); i++) {
                if(promotionalOffers.get(i).getContractId() == enteredContractId) {
                    currentContractPromotionalOffers.add(promotionalOffers.get(i));
                    System.out.print("\n════════════════ PROMOTIONAL OFFER ID: " + promotionalOffers.get(i).getId() + " ════════════════");
                    System.out.print("\n  * Offer Name           : " + promotionalOffers.get(i).getOfferName());
                    System.out.print("\n  * Description          : " + promotionalOffers.get(i).getDescription());
                    System.out.print("\n  * Start Date           : " + promotionalOffers.get(i).getStartDate());
                    System.out.print("\n  * End Date             : " + promotionalOffers.get(i).getEndDate());
                    System.out.print("\n  * Discount Type        : " + promotionalOffers.get(i).getDiscountType());
                    System.out.print("\n  * Discount Value       : " + promotionalOffers.get(i).getDiscountValue());
                    System.out.print("\n  * Conditions           : " + promotionalOffers.get(i).getConditions());
                    System.out.print("\n  * Offer Status         : " + promotionalOffers.get(i).getOfferStatus());
                    System.out.print("\n  * Contract ID          : " + promotionalOffers.get(i).getContractId());
                    System.out.println("\n════════════════════════════════════════════════════════════════");
                    System.out.println();                    
                }
            }
        }else {
            System.out.println("        There is no assigned promotion to that contract!");
        }
        
        return currentContractPromotionalOffers;
    }
}