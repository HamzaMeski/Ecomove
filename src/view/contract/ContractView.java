package view.contract;
import model.entities.Contract;
import java.util.List;

import controller.PartnerController;
import model.dao.PartnerDAO;
import view.partner.PartnerView;
import model.entities.Partner;

import lib.ScanInput;

public class ContractView {

    public void displayAddContractMessage() {
        System.out.println("Contract added successfully!");
    }

    public void listAllContracts(List<Contract> contracts) {
        PartnerDAO model = new PartnerDAO();
        PartnerView view = new PartnerView();
        PartnerController partnerController = new PartnerController(model, view);
        List<Partner> partners = partnerController.listAllPartners();

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

        if(contracts.size() == 0) {
            System.out.println("        There is no contract assigned to that partner currently!");
        }else {
            for(byte i = 0; i < contracts.size(); i++) {
                if(contracts.get(i).getPartnerId() == enteredPartnerId) {
                    System.out.println("++++++++++++++++++++++++ CONTRACT  ++++++++++++++++++++++++");
                    System.out.println("        >> id: "+contracts.get(i).getId());
                    System.out.println("        >> start date: "+contracts.get(i).getStartDate());
                    System.out.println("        >> end date: "+contracts.get(i).getEndDate());
                    System.out.println("        >> special price: "+contracts.get(i).getSpecialPrice());
                    System.out.println("        >> agreement conditions: "+contracts.get(i).getAgreementConditions());
                    System.out.println("        >> contract renewable: "+contracts.get(i).getRenewable());
                    System.out.println("        >> contract status: "+contracts.get(i).getContractStatus());
                    System.out.println("        >> partner id: "+contracts.get(i).getPartnerId());
                    System.out.println("");
                }
            }
        }
    }
 
    public void displayUpdateContractMessage() {
        System.out.println("    Contract updated successfully");
    }
}
