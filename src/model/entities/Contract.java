package model.entities;

import model.enums.ContractStatus;

import java.time.LocalDate;

public class Contract {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float specialPrice;
    private String agreementConditions;
    private Boolean renewable;
    private ContractStatus contractStatus;
    private int partnerId;

    public Contract(
        int id,
        LocalDate startDate,
        LocalDate endDate,
        Float specialPrice,
        String agreementConditions, 
        Boolean renewable,
        ContractStatus contractStatus,
        int partnerId
    )
    {
        this.id = id;
        this.startDate = startDate; 
        this.endDate = endDate; 
        this.specialPrice = specialPrice; 
        this.agreementConditions = agreementConditions; 
        this.renewable = renewable; 
        this.contractStatus = contractStatus;
        this.partnerId = partnerId;
    }

    /*
     * Getters
     */
    
    public int getId() {
        return id;
    }
    
    public LocalDate getStartDate() {
        return startDate;
    }
    
    public LocalDate getEndDate() {
        return endDate;
    }

    public Float getSpecialPrice() {
        return specialPrice;
    }

    public String getAgreementConditions() {
        return agreementConditions;
    }

    public Boolean getRenewable() {
        return renewable;
    }

    public ContractStatus getContractStatus() {
        return contractStatus;
    }

    public int getPartnerId() {
        return partnerId;
    }
    /*
     * Setters
     */

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    } 
    
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setSpecialPrice(float specialPrice) {
        this.specialPrice = specialPrice;
    }

    public void setAgreementConditions(String agreementConditions) {
        this.agreementConditions = agreementConditions;
    }

    public void setRenewable(boolean renewable) {
        this.renewable = renewable;
    }

    public void setContractStatus(ContractStatus contractStatus) {
        this.contractStatus = contractStatus;
    }
    
    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    /*
     * toString Method
    */
    
    @Override
    public String toString() {
        return "Contract {"
        +"id: "+id
        +", startDate: "+ startDate
        +", endDate: "+ endDate
        +", specialPrice: "+ specialPrice
        +", agreementConditions: "+ agreementConditions
        +", renewable: "+ renewable
        +", contractStatus: "+ contractStatus
        +'}';
    }
}