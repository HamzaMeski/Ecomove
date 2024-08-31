package model.entities;

import model.enums.TransportType;
import model.enums.PartnerStatus;

import java.time.LocalDate;

public class Partner {
    private int id;
    private String companyName;
    private String commercialContact; 
    private TransportType transportType;
    private String geographicalArea;
    private String specialConditions;
    private PartnerStatus partnerStatus;
    private LocalDate creationDate;

    public Partner(
        int id,
        String companyName,
        String commercialContact,
        TransportType transportType,
        String geographicalArea,
        String specialConditions,
        PartnerStatus partnerStatus,
        LocalDate creationDate
    )
    {
        this.id = id; 
        this.companyName = companyName; 
        this.commercialContact = commercialContact; 
        this.transportType = transportType; 
        this.geographicalArea = geographicalArea; 
        this.specialConditions = specialConditions; 
        this.partnerStatus = partnerStatus; 
        this.creationDate = creationDate;
    }

    /*
     * Getters
     */

    public int getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCommercialContact() {
        return commercialContact;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public String getGeographicalArea() {
        return geographicalArea;
    }

    public String getSpecialConditions() {
        return specialConditions;
    }

    public PartnerStatus getPartnerStatus() {
        return partnerStatus;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }


    /*
     * Setters
     */

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setCommercialContact(String commercialContact) {
        this.commercialContact = commercialContact;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public void setGeographicalArea(String geographicalArea) {
        this.geographicalArea = geographicalArea;
    }

    public void setSpecialConditions(String specialConditions) {
        this.specialConditions = specialConditions;
    }

    public void setPartnerStatus(PartnerStatus partnerStatus) {
        this.partnerStatus = partnerStatus;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /*
     * toString Method
     */

    @Override
    public String toString() {
        return "Partner{" +
                "id=" + id +
                ", companyName='" + companyName +
                ", commercialContact='" + commercialContact +
                ", transportType=" + transportType +
                ", geographicalArea='" + geographicalArea +
                ", specialConditions='" + specialConditions +
                ", partnerStatus=" + partnerStatus +
                ", creationDate=" + creationDate +
                '}';
    }
} 