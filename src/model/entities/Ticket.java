package model.entities; 

import java.time.LocalDate;
import java.time.LocalDateTime;

import model.enums.TicketStatus;
import model.enums.TransportType; 

public class Ticket {
    private int id; 
    private String transportType; 
    private Float purchasePrice;
    private Float salePrice; 
    private LocalDate saleDate; 
    private TicketStatus ticketStatus;
    private String departure;
    private LocalDateTime departureTime;
    private String destination;
    private LocalDateTime destinationTime;
    private int contractId;

    public Ticket(
        int id,
        String transportType,
        Float purchasePrice,
        Float salePrice,
        LocalDate saleDate, 
        TicketStatus ticketStatus,
        String departure, 
        LocalDateTime departureTime,
        String destination,
        LocalDateTime destinationTime, 
        int contractId
    )
    {
        this.id = id; 
        this.transportType = transportType;
        this.purchasePrice = purchasePrice; 
        this.salePrice = salePrice; 
        this.saleDate = saleDate; 
        this.ticketStatus = ticketStatus;
        this.departure = departure; 
        this.departureTime = departureTime; 
        this.destination = destination;
        this.destinationTime = destinationTime; 
        this.contractId = contractId;
    }

    /*
     * Getters
     */
    
    public int getId() {
        return id;
    }

    public String getTransportType() {
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

    public String getDeparture() {
        return departure;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDateTime getDestinationTime() {
        return destinationTime;
    }

    public int getContractId() {
        return contractId;
    }

    /*
     * Setters
    */

    public void setTransportType(String transportType) {
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

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDestinationTime(LocalDateTime destinationTime) {
        this.destinationTime = destinationTime;
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
                ", departure=" + departure +
                ", departureTime=" + departureTime +
                ", destination=" + destination +
                ", destinationTime=" + destinationTime +
                '}';
    }
}
