package model.dao;
import model.DbConfig;

import model.entities.Partner;

import java.sql.*;
import java.util.List; 
import java.util.ArrayList; 

public class PartnerDAO {

    public void addPartner(Partner partner) {
        String sql = "INSERT INTO partner (name, contact_name, transport_type, geographic_area, special_conditions, status, creation_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, partner.getCompanyName());
            stmt.setString(2, partner.getCommercialContact());
            stmt.setString(3, partner.getTransportType().toString());
            stmt.setString(4, partner.getGeographicalArea());
            stmt.setString(5, partner.getSpecialConditions());
            stmt.setString(6, partner.getPartnerStatus().toString());
            stmt.setDate(7, Date.valueOf(partner.getCreationDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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