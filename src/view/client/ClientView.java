package view.client;

import model.entities.Client;
import java.util.List;

public class ClientView {
    public void displayAddClientMessage() {
        System.out.println("    Client added successfully!");
    }

    public void displayUpdateClientMessage() {
        System.out.println("    Client updated successfully!");
    }

    public void displayDeleteClientMessage() {
        System.out.println("    Client deleted successfully!");
    }

    public void listAllClients(List<Client> clients) {
        for(Client client: clients) {
            System.out.print("\n════════════════ CLIENT ID: " + client.getId()+" ═════════════════");
            System.out.print("\n  * first name: "+      client.getFirstName());
            System.out.print("\n  * second name: "+      client.getSecondName());
            System.out.print("\n  * phone number: "+      client.getPhoneNumber());
            System.out.print("\n  * email: "+      client.getEmail());         
        }
    }
}