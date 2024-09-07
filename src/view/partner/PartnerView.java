package view.partner; 

import controller.PartnerController;
import model.entities.Partner;
import java.util.List;

public class PartnerView {
    public void showAddPartnerMessage() {
        System.out.println("Partner added successfully!");
    }

    public void listAllPartners(List<Partner> partners) {
        if(partners.size() == 0) {
            System.out.println("        There is no partner currently!");
        }else {
            for(byte i = 0; i < partners.size(); i++) {
                System.out.print("\n════════════════ Company Name: << " + partners.get(i).getCompanyName() + " >> ════════════════");
                System.out.print("\n  * Partner ID            : " + partners.get(i).getId());
                System.out.print("\n  * Company Name          : " + partners.get(i).getCompanyName());
                System.out.print("\n  * Commercial Contact    : " + partners.get(i).getCommercialContact());
                System.out.print("\n  * Transport Type        : " + partners.get(i).getTransportType());
                System.out.print("\n  * Geographical Area     : " + partners.get(i).getGeographicalArea());
                System.out.print("\n  * Special Conditions    : " + partners.get(i).getSpecialConditions());
                System.out.print("\n  * Partner Status        : " + partners.get(i).getPartnerStatus());
                System.out.print("\n  * Creation Date         : " + partners.get(i).getCreationDate());
                System.out.println("\n════════════════════════════════════════════════════════════════");
                System.out.println();                
            }
        }
    }

    public void showDeletePartnerMessage() {
        System.out.println("Partner deleted successfully!");
    }

    public void showUpdatePartnerMessage() {
        System.out.println("Partner updated successfully!");
    }

    public void displayParnter(Partner partner) {
        System.out.println(partner);
    }
}