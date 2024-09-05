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