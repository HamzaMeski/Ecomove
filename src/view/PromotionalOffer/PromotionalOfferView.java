package view.PromotionalOffer;

import java.util.List;

import lib.ScanInput;

import java.util.ArrayList;
import model.entities.PromotionalOffer;
import src.controller.PartnerController;
import src.model.dao.PartnerDAO;
import src.model.entities.Partner;
import src.view.partner.PartnerView;

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

    public void listAllPromotionalOffers(List<PromotionalOffer> promotionalOffers) {
        ContractDAO model = new ContractDAO();
        ContractView view = new ContractView();
        ContractController contractController = new ContractController(model, view);
        List<Contract> contracts = contractController.listAllContracts();

        boolean idExists;
        int enteredContractId;
        do {
            System.out.print("      Set the id of the contract that you want to showcase its promotions: ");
            int enteredId = ScanInput.scanner.nextInt();
            enteredContractId = enteredId;
            ScanInput.scanner.nextLine();
            idExists = contracts.stream().anyMatch(contract -> contract.getId() == enteredId);
            
            if (!idExists) {
                System.out.println("        The entered ID does not exist. Please try again!");
            }
        } while (!idExists);

        if(promotionalOffers.size() == 0) {
            System.out.println("        There is no assigned promotion to that contract!");
        }else {
            for(byte i = 0; i < promotionalOffers.size(); i++) {
                if(promotionalOffers.get(i).getContractId() == enteredContractId) {
                    System.out.println("++++++++++++++++++++++++ PROMOTIONAL OFFER  ++++++++++++++++++++++++");
                    System.out.println("        >> id: "+promotionalOffers.get(i).getId());
                    System.out.println("        >> offer name : "+promotionalOffers.get(i).getOfferName());
                    System.out.println("        >> description : "+promotionalOffers.get(i).getDescription());
                    System.out.println("        >> start date: "+promotionalOffers.get(i).getStartDate());
                    System.out.println("        >> end date: "+promotionalOffers.get(i).getEndDate());
                    System.out.println("        >> discount type: "+promotionalOffers.get(i).getDiscountType());
                    System.out.println("        >> get discount value: "+promotionalOffers.get(i).getDiscountValue());
                    System.out.println("        >> get conditions: "+promotionalOffers.get(i).getConditions());
                    System.out.println("        >> get offer status: "+promotionalOffers.get(i).getOfferStatus());
                    System.out.println("        >> get contact id: "+promotionalOffers.get(i).getContractId());
                    System.out.println("");
                }
            }
        }
    }
}
