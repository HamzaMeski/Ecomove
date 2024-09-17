package schedule;

import java.sql.*;

public class ContractUpdater {
    public static void updateContracts() {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = DbConfig.getConnection();

            stmt = conn.createStatement();

            String sql = "UPDATE Contract SET status = 'COMPLETED' " +
                         "WHERE end_date < CURRENT_DATE AND status <> 'COMPLETED';";
            int rowsUpdated = stmt.executeUpdate(sql);

            System.out.println("Contracts updated: " + rowsUpdated);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        updateContracts();
    }
}

class DbConfig {
    private static final String URL = "jdbc:postgresql://localhost:5432/ecomove2";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
