package controller;

import java.util.List;

import model.entities.PromotionalOffer;
import model.dao.PromotionalOfferDAO;
import view.PromotionalOffer.PromotionalOfferView;;

public class PromotionalOfferController {
    private PromotionalOfferDAO model;
    private PromotionalOfferView view;

    public PromotionalOfferController(PromotionalOfferDAO model, PromotionalOfferView view) {
        this.model = model; 
        this.view = view;
    }

    public void addPromotionalOffer(PromotionalOffer promotionalOffer) {
        model.addPromotionalOffer(promotionalOffer);
        view.showAddPromotionMessage();
    }

    public void updatePromotionalOffer(PromotionalOffer promotionalOffer) {
        model.updatePromotionalOffer(promotionalOffer);
        view.showUpdatePromotionMessage();
    }

    public List<PromotionalOffer> listContractPromotionalOffers() { 
        List<PromotionalOffer> promotionalOffers = model.listAllPromotionalOffers();
        List<PromotionalOffer> contractPromotionalOffers =  view.listContractPromotionalOffers(promotionalOffers);
        return contractPromotionalOffers;
    }
}
 