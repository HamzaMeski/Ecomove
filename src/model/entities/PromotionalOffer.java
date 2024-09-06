package model.entities; 

import java.time.LocalDate;

import model.enums.DiscountType;
import model.enums.OfferStatus;


public class PromotionalOffer {
    private int id;
    private String offerName;
    private String description; 
    private LocalDate startDate; 
    private LocalDate endDate;
    private DiscountType discountType;
    private Float discountValue;
    private String conditions;
    private OfferStatus offerStatus;
    private int contractId;


    public PromotionalOffer( 
        int id,
        String offerName,
        String description, 
        LocalDate startDate, 
        LocalDate endDate,
        DiscountType discountType,
        Float discountValue,
        String conditions,
        OfferStatus offerStatus,
        int contractId
    )
    {
        this.id = id; 
        this.offerName = offerName; 
        this.description = description; 
        this.startDate = startDate; 
        this.endDate = endDate; 
        this.discountType = discountType; 
        this.discountValue = discountValue; 
        this.conditions = conditions; 
        this.offerStatus = offerStatus;
        this.contractId = contractId;
    }

      /*
     * Getters
     */
    
     public int getId() {
        return id;
    }

    public String getOfferName() {
        return offerName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public Float getDiscountValue() {
        return discountValue;
    }

    public String getConditions() {
        return conditions;
    }

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public int getContractId() {
        return contractId;
    }

    /*
     * Setters
     */

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public void setDiscountValue(float discountValue) {
        this.discountValue = discountValue;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }
     
    public void setContractId(int contractId) {
        this.contractId =  contractId;
    }

    /*
     * toString Method
    */

    @Override
    public String toString() {
        return "PromotionalOffer{" +
                "id=" + id +
                ", offerName='" + offerName +
                ", description='" + description +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", discountType=" + discountType +
                ", discountValue=" + discountValue +
                ", conditions='" + conditions +
                ", offerStatus=" + offerStatus +
                '}';
    }
}