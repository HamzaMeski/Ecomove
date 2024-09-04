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

    public int addPartner(Partner partner) {
        int insertedPartnerId = model.addPartner(partner);
        view.showAddPartnerMessage();
        return insertedPartnerId;
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

    public List<Partner> listAllPartners() {
        List<Partner> partners = model.listAllPartners();
        view.listAllPartners(partners);
        return partners;
    }
}