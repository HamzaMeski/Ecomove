package view.partner;

import controller.PartnerController;
import model.dao.PartnerDAO;
import view.partner.PartnerView;

public class PartnerMenu {

    public static void displayMenu() {
        PartnerDAO model = new PartnerDAO(); 
        PartnerView view = new PartnerView();
        PartnerController partnerController = new PartnerController(model, view);
    }
}
