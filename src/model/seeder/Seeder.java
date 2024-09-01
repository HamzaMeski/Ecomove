package model.seeder;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

import model.DbConfig;

public class Seeder {
    
    public static void main(String[] args) {
    
        Connection connection = null;
        Statement statement = null;
        
        try {
            connection = DbConfig.getConnection();
            statement = connection.createStatement();
            

            String createPartnerTable = "CREATE TABLE IF NOT EXISTS Partner (" +
                "id SERIAL PRIMARY KEY, " + 
                "name VARCHAR(255) NOT NULL, " +
                "contact_name VARCHAR(255), " +
                "transport_type VARCHAR(50) CHECK (transport_type IN ('PLANE', 'TRAIN', 'BUS')), " +
                "geographic_area VARCHAR(255), " +
                "special_conditions TEXT, " +
                "status VARCHAR(50) CHECK (status IN ('ACTIVE', 'INACTIVE', 'SUSPENDED')), " +
                "creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";

            String createContractTable = "CREATE TABLE IF NOT EXISTS Contract (" +
                "id SERIAL PRIMARY KEY, " + 
                "start_date DATE NOT NULL, " +
                "end_date DATE, " +
                "special_rate DECIMAL(10, 2), " + 
                "contract_conditions TEXT, " +
                "renewable BOOLEAN, " +
                "status VARCHAR(50) CHECK (status IN ('ONGOING', 'COMPLETED', 'SUSPENDED')), " +
                "partner_id INTEGER REFERENCES Partner(id)" +
            ")";
            
            String createPromotionalOfferTable = "CREATE TABLE IF NOT EXISTS PromotionalOffer (" +
                "id SERIAL PRIMARY KEY, " + 
                "offer_name VARCHAR(255) NOT NULL, " +
                "description TEXT, " +
                "start_date DATE, " +
                "end_date DATE, " +
                "discount_type VARCHAR(50) CHECK (discount_type IN ('PERCENTAGE', 'FIXED_AMOUNT')), " +
                "discount_value DECIMAL(10, 2), " + 
                "conditions TEXT, " +
                "status VARCHAR(50) CHECK (status IN ('ACTIVE', 'EXPIRED', 'SUSPENDED')), " +
                "contract_id INTEGER REFERENCES Contract(id)" +  
            ")";
            
            String createTicketTable = "CREATE TABLE IF NOT EXISTS Ticket (" +
                "id SERIAL PRIMARY KEY, " + 
                "transport_type VARCHAR(50) CHECK (transport_type IN ('PLAN', 'TRAIN', 'BUS')), " +
                "purchase_price DECIMAL(10, 2) NOT NULL, " + 
                "sale_price DECIMAL(10, 2) NOT NULL, " + 
                "sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "status VARCHAR(50) CHECK (status IN ('SOLD', 'CANCELED', 'PENDING')), " +
                "contract_id INTEGER REFERENCES Contract(id)" + 
            ")";
            
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
