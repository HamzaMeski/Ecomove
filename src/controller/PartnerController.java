package controller;

import model.entities.Partner;
import model.dao.PartnerDAO;
import view.partner.PartnerView;

public class PartnerController {
    private PartnerDAO model;
    private PartnerView view;
     
    public PartnerController(PartnerDAO model, PartnerView view) {
        this.model = model; 
        this.view = view;
    }

    public void addPartner(Partner partner) {
        // System.out.println(partner);
        model.addPartner(partner);
        view.showAddPartnerMessage();
    }
     
    public void updatePartner() {

    }

    public void deletePartner() {

    }

    public void displayPartner() {

    }

    public void listAllPartners() {

    }
}

