package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.DbConfig;
import model.entities.PromotionalOffer;
import model.enums.DiscountType;
import model.enums.OfferStatus;

import java.sql.*;

public class PromotionalOfferDAO {
    public void addPromotionalOffer(PromotionalOffer promotionalOffer) {
        String sql = "INSERT INTO PromotionalOffer (offer_name, description, start_date, end_date, discount_type, discount_value, conditions, status, contract_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set all the parameters for the PreparedStatement
            stmt.setString(1, promotionalOffer.getOfferName());
            stmt.setString(2, promotionalOffer.getDescription());
            stmt.setDate(3, Date.valueOf(promotionalOffer.getStartDate()));
            stmt.setDate(4, Date.valueOf(promotionalOffer.getEndDate()));
            stmt.setString(5, promotionalOffer.getDiscountType().toString());
            stmt.setFloat(6, promotionalOffer.getDiscountValue());
            stmt.setString(7, promotionalOffer.getConditions());
            stmt.setString(8, promotionalOffer.getOfferStatus().toString());
            stmt.setInt(9, promotionalOffer.getContractId());

            // Execute the insert statement
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePromotionalOffer(PromotionalOffer promotionalOffer) {
        System.out.println("???§????");
        System.out.println(promotionalOffer);
        System.out.println("???§????");
        
        List<String> sqlColumns = new ArrayList<>();
        int counter = 0;
    
        if (promotionalOffer.getOfferName() != null) {
            sqlColumns.add("offer_name = ?");
            counter++;
        }
        if (promotionalOffer.getDescription() != null) {
            sqlColumns.add("description = ?");
            counter++;
        }
        if (promotionalOffer.getStartDate() != null) {
            sqlColumns.add("start_date = ?");
            counter++;
        }
        if (promotionalOffer.getEndDate() != null) {
            sqlColumns.add("end_date = ?");
            counter++;
        }
        if (promotionalOffer.getDiscountType() != null) {
            sqlColumns.add("discount_type = ?");
            counter++;
        }
        if (promotionalOffer.getDiscountValue() != null) {
            sqlColumns.add("discount_value = ?");
            counter++;
        }
        if (promotionalOffer.getConditions() != null) {
            sqlColumns.add("conditions = ?");
            counter++;
        }
        if (promotionalOffer.getOfferStatus() != null) {
            sqlColumns.add("status = ?");
            counter++;
        }
    
        if (counter == 0) {
            System.out.println("##############");
            System.out.println(counter);
            System.out.println("##############");
            return;
        }
    
        String sql = "UPDATE PromotionalOffer SET ";
        sql += String.join(", ", sqlColumns);
        sql += " WHERE id = ?";
    
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
    
            int parameterIndex = 1;

            if (promotionalOffer.getOfferName() != null) {
                stmt.setString(parameterIndex++, promotionalOffer.getOfferName());
            }
            if (promotionalOffer.getDescription() != null) {
                stmt.setString(parameterIndex++, promotionalOffer.getDescription());
            }
            if (promotionalOffer.getStartDate() != null) {
                stmt.setDate(parameterIndex++, Date.valueOf(promotionalOffer.getStartDate()));
            }
            if (promotionalOffer.getEndDate() != null) {
                stmt.setDate(parameterIndex++, Date.valueOf(promotionalOffer.getEndDate()));
            }
            if (promotionalOffer.getDiscountType() != null) {
                stmt.setString(parameterIndex++, promotionalOffer.getDiscountType().toString());
            }
            if (promotionalOffer.getDiscountValue() != null) {
                stmt.setFloat(parameterIndex++, promotionalOffer.getDiscountValue());
            }
            if (promotionalOffer.getConditions() != null) {
                stmt.setString(parameterIndex++, promotionalOffer.getConditions());
            }
            if (promotionalOffer.getOfferStatus() != null) {
                stmt.setString(parameterIndex++, promotionalOffer.getOfferStatus().toString());
            }
    
            // Set the ID for the WHERE clause
            stmt.setInt(parameterIndex, promotionalOffer.getId());
            System.out.println("#############################");
            System.out.println(promotionalOffer.getId());
            System.out.println("#############################");
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    

    public List<PromotionalOffer> listAllPromotionalOffers() {
        List<PromotionalOffer> promotionalOffers = new ArrayList<>();
        String sql = "SELECT * FROM PromotionalOffer";
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                PromotionalOffer promotionalOffer = new PromotionalOffer(
                    rs.getInt("id"),
                    rs.getString("offer_name"),
                    rs.getString("description"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    DiscountType.valueOf(rs.getString("discount_type")),
                    rs.getFloat("discount_value"),
                    rs.getString("conditions"),
                    OfferStatus.valueOf(rs.getString("status")),
                    rs.getInt("contract_id")
                    );
                promotionalOffers.add(promotionalOffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotionalOffers;
    }
}