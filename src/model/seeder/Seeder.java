package model.seeder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Seeder {
    
    public static void main(String[] args) {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/";
        String username = "postgres";
        String password = "password";
        String dbName = "ecomove";
        Connection connection = null;
        Statement statement = null;
        
        try {
            // Connect to PostgreSQL server and select the database
            connection = DriverManager.getConnection(jdbcUrl + dbName, username, password);
            statement = connection.createStatement();
            
            // Create tables
            String createPartnerTable = "CREATE TABLE IF NOT EXISTS Partner (" +
                "id UUID PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "contact_name VARCHAR(255), " +
                "transport_type VARCHAR(50) CHECK (status IN ('PLAN', 'TRAIN', 'BUS')), " +
                "geographic_area VARCHAR(255), " +
                "special_conditions TEXT, " +
                "status VARCHAR(50) CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED')), " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

            String createContractTable = "CREATE TABLE IF NOT EXISTS Contract (" +
                "id UUID PRIMARY KEY, " +
                "start_date DATE NOT NULL, " +
                "end_date DATE, " +
                "special_rate DECIMAL(10, 2), " + 
                "contract_conditions TEXT, " +
                "renewable BOOLEAN, " +
                "status VARCHAR(50) CHECK (status IN ('ONGOING', 'COMPLETED', 'SUSPENDED')), " +
                "partner_id UUID REFERENCES Partner(id)" +
            ")";
            
            String createPromotionalOfferTable = "CREATE TABLE IF NOT EXISTS PromotionalOffer (" +
                "id UUID PRIMARY KEY, " +
                "offer_name VARCHAR(255) NOT NULL, " +
                "description TEXT, " +
                "start_date DATE, " +
                "end_date DATE, " +
                "discount_type VARCHAR(50) CHECK (status IN ('PERCENTAGE', 'FIXED_AMOUNT')), " +
                "discount_value DECIMAL(10, 2), " + 
                "conditions TEXT, " +
                "status VARCHAR(50) CHECK (status IN ('ACTIVE', 'EXPIRED', 'SUSPENDED')), " +
                "contract_id UUID REFERENCES Contract(id)" +
            ")";
            
            String createTicketTable = "CREATE TABLE IF NOT EXISTS Ticket (" +
                "id UUID PRIMARY KEY, " +
                "transport_type VARCHAR(50) CHECK (status IN ('PLAN', 'TRAIN', 'BUS')), " +
                "purchase_price DECIMAL(10, 2) NOT NULL, " + 
                "sale_price DECIMAL(10, 2) NOT NULL, " + 
                "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "status VARCHAR(50) CHECK (status IN ('SOLD', 'CANCELED', 'PENDING')), " +
                "contract_id UUID REFERENCES Contract(id)" +
            ")";
            
            // Execute table creation
            statement.execute(createPartnerTable);
            statement.execute(createContractTable);
            statement.execute(createPromotionalOfferTable);
            statement.execute(createTicketTable);
            
            System.out.println("Database seeded successfully.");
        } catch (SQLException e) {
            System.err.println("Error during database seeding: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
