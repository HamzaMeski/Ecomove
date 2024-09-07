package model.entities; 

import java.time.LocalDate;

import model.enums.TicketStatus;
import model.enums.TransportType; 

public class Ticket {
    private int id; 
    private TransportType transportType; 
    private Float purchasePrice;
    private Float salePrice; 
    private LocalDate saleDate; 
    private TicketStatus ticketStatus;
    private int contractId;

    public Ticket(
        int id,
        TransportType transportType,
        Float purchasePrice,
        Float salePrice,
        LocalDate saleDate, 
        TicketStatus ticketStatus,
        int contractId
    )
    {
        this.id = id; 
        this.transportType = transportType;
        this.purchasePrice = purchasePrice; 
        this.salePrice = salePrice; 
        this.saleDate = saleDate; 
        this.ticketStatus = ticketStatus;
        this.contractId = contractId;
    }

    /*
     * Getters
     */
    
     public int getId() {
        return id;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public Float getPurchasePrice() {
        return purchasePrice;
    }

    public Float getSalePrice() {
        return salePrice;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public TicketStatus getTicketStatus() {
        return ticketStatus;
    }

    public int getContractId() {
        return contractId;
    }

    /*
     * Setters
    */

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public void setPurchasePrice(float purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSalePrice(float salePrice) {
        this.salePrice = salePrice;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public void setTicketStatus(TicketStatus ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    /*
     * toString Method
    */

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", transportType=" + transportType +
                ", purchasePrice=" + purchasePrice +
                ", salePrice=" + salePrice +
                ", saleDate=" + saleDate +
                ", ticketStatus=" + ticketStatus +
                '}';
    }
}