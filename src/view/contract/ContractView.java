package view.contract;
import model.entities.Contract;
import java.util.List;
import java.util.ArrayList;

import controller.PartnerController;
import model.dao.PartnerDAO;
import view.partner.PartnerView;
import model.entities.Partner;

import lib.ScanInput;

public class ContractView {

    public void displayAddContractMessage() {
        System.out.println("Contract added successfully!");
    }

    public List<Contract> listAllContracts(List<Contract> contracts) {
        PartnerDAO model = new PartnerDAO();
        PartnerView view = new PartnerView();
        PartnerController partnerController = new PartnerController(model, view);
        List<Partner> partners = partnerController.listAllPartners();
        
        List<Contract> partnerContracts = new ArrayList<>(List.of());
        
        if(partners.size() != 0) {
            boolean idExists;
            int enteredPartnerId;
            do {
                System.out.print("      Set the id of the company that you want to showcase its contracts: ");
                int enteredId = ScanInput.scanner.nextInt();
                enteredPartnerId = enteredId;
                ScanInput.scanner.nextLine();
                idExists = partners.stream().anyMatch(partner -> partner.getId() == enteredId);
                
                if (!idExists) {
                    System.out.println("        The entered ID does not exist. Please try again!");
                }
            } while (!idExists);
    
            int partnerIdFinal = enteredPartnerId;
            boolean isContractsNotEmpty = contracts.stream().anyMatch( contract -> contract.getPartnerId() == partnerIdFinal );
            if(isContractsNotEmpty) {
                for(byte i = 0; i < contracts.size(); i++) {
                    if(contracts.get(i).getPartnerId() == enteredPartnerId) {
                        partnerContracts.add(contracts.get(i));

                        System.out.print("\n════════════════ CONTRACT ID: " + contracts.get(i).getId() + " ════════════════");
                        System.out.print("\n  * Start Date            : " + contracts.get(i).getStartDate());
                        System.out.print("\n  * End Date              : " + contracts.get(i).getEndDate());
                        System.out.print("\n  * Special Price         : " + contracts.get(i).getSpecialPrice());
                        System.out.print("\n  * Agreement Conditions  : " + contracts.get(i).getAgreementConditions());
                        System.out.print("\n  * Renewable             : " + contracts.get(i).getRenewable());
                        System.out.print("\n  * Contract Status       : " + contracts.get(i).getContractStatus());
                        System.out.print("\n  * Partner ID            : " + contracts.get(i).getPartnerId());
                        System.out.println("\n════════════════════════════════════════════════════════════════");
                        System.out.println();
                    }
                }
            }else {
                System.out.println("        There is no contract assigned to that partner currently!");
            }
        }

        return partnerContracts;
    }
 
    public void displayUpdateContractMessage() {
        System.out.println("    Contract updated successfully");
    }
}