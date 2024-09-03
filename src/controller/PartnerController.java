package controller;

import model.entities.Partner;
import model.dao.PartnerDAO;
import view.partner.PartnerView;
import lib.ScanInput;


import java.util.List; 
import java.util.ArrayList; 

public class PartnerController {
    private PartnerDAO model;
    private PartnerView view;
     
    public PartnerController(PartnerDAO model, PartnerView view) {
        this.model = model; 
        this.view = view;
    }

    public void addPartner(Partner partner) {
        model.addPartner(partner);
        view.showAddPartnerMessage();
    }
     
    public void updatePartner(Partner partner) {
        model.updatePartner(partner);
        view.showUpdatePartnerMessage();
    }

    public void deletePartner(int partnerId) {
        model.deletePartner(partnerId);
        view.showDeletePartnerMessage();
    }

    public void searchPartner(String partnerName) {
        Partner partner = model.searchPartner(partnerName);
        view.displayParnter(partner);
    }

    public void listAllPartners() {
        List<Partner> partners = model.listAllPartners();
        view.listAllPartners(partners);
    }
}