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
                "special_price DECIMAL(10, 2), " + 
                "agreement_conditions TEXT, " +
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
                "transport_type VARCHAR(50) CHECK (transport_type IN ('PLANE', 'TRAIN', 'BUS')), " +
                "purchase_price DECIMAL(10, 2) NOT NULL, " + 
                "sale_price DECIMAL(10, 2) NOT NULL, " + 
                "sale_date TIMESTAMP, " +
                "status VARCHAR(50) CHECK (status IN ('SOLD', 'CANCELED', 'PENDING')), " +
                "departure VARCHAR(50), "+
                "departure_time TIMESTAMP, "+
                "destination VARCHAR(50), "+
                "destination_time TIMESTAMP, "+
                "contract_id INTEGER REFERENCES Contract(id)" + 
            ")";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin (" + 
                "id SERIAL PRIMARY KEY, " + 
                "first_name VARCHAR(255) NOT NULL, " +
                "second_name VARCHAR(255) NOT NULL, " +
                "phone_number VARCHAR(255) NOT NULL, " +
                "email VARCHAR(255) NOT NULL " +
            ")";

            String createClientTable = "CREATE TABLE IF NOT EXISTS Client (" + 
                "id SERIAL PRIMARY KEY, " + 
                "first_name VARCHAR(255) NOT NULL, " +
                "second_name VARCHAR(255) NOT NULL, " +
                "phone_number VARCHAR(255) NOT NULL, " +
                "password VARCHAR(255) NOT NULL, " +
                "email VARCHAR(255) NOT NULL " +
            ")";

            // GRAPH CITIES NETWORK TABLES
            String createVertixe1Table = "CREATE TABLE vertixe1 (" +
                "id SERIAL PRIMARY KEY, " +
                "departure VARCHAR(255) NOT NULL, " +
                "departure_time TIMESTAMP NOT NULL " +
            ")";

            String createVertixe2Table = "CREATE TABLE vertixe2 (" +
                "id SERIAL PRIMARY KEY, " +
                "vertixe1_id INT NOT NULL, " +
                "destination VARCHAR(255) NOT NULL, " +
                "destination_time TIMESTAMP NOT NULL, " +
                "ticket_id INT NOT NULL, " +
                "FOREIGN KEY (vertixe1_id) REFERENCES vertixe1(id), " +
                "FOREIGN KEY (ticket_id) REFERENCES Ticket(id) " +
            ")";

            // Create Reservation table
            String createReservationTable = "CREATE TABLE reservation (" +
                "id SERIAL PRIMARY KEY, " + 
                "ticket_id INT NOT NULL, " +
                "client_id INT NOT NULL, " +
                "reservation_time TIMESTAMP NOT NULL, " +
                "FOREIGN KEY (ticket_id) REFERENCES Ticket(id), " +
                "FOREIGN KEY (client_id) REFERENCES Client(id) " +
            ")";

            // Create User session table
            String createUserSessionTable = "CREATE TABLE user_session (" +
                "id SERIAL PRIMARY KEY, " +
                "user_id INT NOT NULL " +
            ")"; 

            statement.execute(createPartnerTable);
            statement.execute(createContractTable);
            statement.execute(createPromotionalOfferTable);
            statement.execute(createTicketTable);
            statement.execute(createAdminTable);
            statement.execute(createClientTable);
            // EXECURE GRAPH TABLES
            statement.execute(createVertixe1Table);
            statement.execute(createVertixe2Table);
            // EXECUTE RESERVATION TABLE
            statement.execute(createReservationTable);
            // EXECUTE USER SESSION TABLE
            statement.execute(createUserSessionTable);

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
