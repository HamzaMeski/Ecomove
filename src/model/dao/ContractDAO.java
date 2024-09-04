package model.dao;
import model.DbConfig;
import model.entities.Contract;
import model.enums.ContractStatus;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class ContractDAO {
    
    public void addContract(Contract contract) {
        String sql = "INSERT INTO contract (start_date, end_date, special_price, agreement_conditions, renewable, status, partner_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, Date.valueOf(contract.getStartDate()));
            stmt.setDate(2, Date.valueOf(contract.getEndDate()));
            stmt.setFloat(3, contract.getSpecialPrice());
            stmt.setString(4, contract.getAgreementConditions());
            stmt.setBoolean(5, contract.getRenewable());
            stmt.setString(6, contract.getContractStatus().toString());
            stmt.setInt(7, contract.getPartnerId());
            stmt.executeUpdate();
        } catch (SQLException e) { 
            e.printStackTrace();
        }
    }

public List<Contract> listAllContracts() {
        List<Contract> contracts = new ArrayList<>();
        String sql = "SELECT * FROM contract";
        try (Connection conn = DbConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contract contract = new Contract(
                    rs.getInt("id"),
                    rs.getDate("start_date").toLocalDate(),
                    rs.getDate("end_date").toLocalDate(),
                    rs.getFloat("special_price"),
                    rs.getString("agreement_conditions"),
                    rs.getBoolean("renewable"),
                    ContractStatus.valueOf(rs.getString("status")),
                    rs.getInt("partner_id")
                    );
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contracts;
    }
}