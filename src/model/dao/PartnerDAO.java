package model.dao;
import model.DbConfig;

import model.entities.Partner;
import model.enums.PartnerStatus;
import model.enums.TransportType;

import java.sql.*;
import java.util.List; 
import java.util.ArrayList; 
import java.time.LocalDate;

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

    public void updatePartner(Partner partner) {
        System.out.println(partner.getCompanyName());
        System.out.println(partner.getGeographicalArea());

        List<String> sqlColumns = new ArrayList<>();

        byte counter = 0;
        if(partner.getCompanyName() != null) {
            sqlColumns.add(" name = ? ");
            counter++;
        }
        if(partner.getCommercialContact() != null) {
            sqlColumns.add(" contact_name = ? "); 
            counter++;
        }
        if(partner.getTransportType() != null) {
            sqlColumns.add(" transport_type = ? ");
            counter++;
        }
        if(partner.getGeographicalArea() != null) {
            sqlColumns.add(" geographic_area = ? ");
            counter++;
        }
        if(partner.getSpecialConditions() != null) {
            sqlColumns.add(" special_conditions = ? ");
            counter++;
        }
        if(partner.getPartnerStatus() != null) {
            sqlColumns.add( " status = ? ");
            counter++;
        }

        String sql = "UPDATE partner SET";
        for(byte i = 0; i < counter; i++) {
            if(i != counter - 1) sql += sqlColumns.get(i) + ", ";
            else sql += sqlColumns.get(i);
        }
        sql+= "WHERE id = ?";

        try (Connection conn = DbConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            byte parametreIndex = 0;
            if(partner.getCompanyName() != null) {
                parametreIndex++;
                stmt.setString(parametreIndex, partner.getCompanyName());
            }
            if(partner.getCommercialContact() != null) {
                parametreIndex++;
                stmt.setString(parametreIndex, partner.getCommercialContact());
            }
            if(partner.getTransportType() != null) {
                parametreIndex++;
                stmt.setString(parametreIndex, partner.getTransportType().toString());
            }
            if(partner.getGeographicalArea() != null) {
                parametreIndex++;
                stmt.setString(parametreIndex, partner.getGeographicalArea());
            }
            if(partner.getSpecialConditions() != null) {
                parametreIndex++;
                stmt.setString(parametreIndex, partner.getSpecialConditions());
            }
            if(partner.getPartnerStatus() != null) {
                parametreIndex++;
                stmt.setString(parametreIndex, partner.getPartnerStatus().toString());
            }
            
            parametreIndex++;
            stmt.setInt(parametreIndex, partner.getId()); 
            
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    public void deletePartner(int partnerId) {
        String sql = "DELETE FROM partner WHERE id = ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, partnerId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Partner searchPartner(String partnerName) {
        String sql = "SELECT * FROM Partner WHERE name LIKE ?";
        Partner partner = null;
    
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            stmt.setString(1, "%" + partnerName.toUpperCase() + "%");
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String contactName = rs.getString("contact_name");
                TransportType transportType = TransportType.valueOf(rs.getString("transport_type"));
                String geographicArea = rs.getString("geographic_area");
                String specialConditions = rs.getString("special_conditions");
                PartnerStatus status = PartnerStatus.valueOf(rs.getString("status"));
                LocalDate creationDate = rs.getDate("creation_date").toLocalDate();
    
                partner = new Partner(id, name, contactName, transportType, geographicArea,
                                      specialConditions, status, creationDate);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        // Return the partner or null if no match was found
        return partner;
    }

    public List<Partner> listAllPartners() {
        List<Partner> partners = new ArrayList<>();
        String sql = "SELECT * FROM partner";
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Partner partner = new Partner(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("contact_name"),
                    TransportType.valueOf(rs.getString("transport_type")),
                    rs.getString("geographic_area"),
                    rs.getString("special_conditions"),
                    PartnerStatus.valueOf(rs.getString("status")),
                    rs.getDate("creation_date").toLocalDate()
                    );
                partners.add(partner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return partners;
    }
}