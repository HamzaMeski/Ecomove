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
                System.out.println("++++++++++++++++++++++++ Company Name:  << "+partners.get(i).getCompanyName()+" >> ++++++++++++++++++++++++");
                System.out.println("        >>"+partners.get(i).getId());
                System.out.println("        >>"+partners.get(i).getCompanyName());
                System.out.println("        >>"+partners.get(i).getCommercialContact());
                System.out.println("        >>"+partners.get(i).getTransportType());
                System.out.println("        >>"+partners.get(i).getGeographicalArea());
                System.out.println("        >>"+partners.get(i).getSpecialConditions());
                System.out.println("        >>"+partners.get(i).getPartnerStatus());
                System.out.println("        >>"+partners.get(i).getCreationDate());
                System.out.println("");
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