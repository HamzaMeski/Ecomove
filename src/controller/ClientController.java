package controller;

import model.entities.Client;
import model.dao.ClientDAO;
import view.client.ClientView;

import java.util.List; 
import java.util.ArrayList; 

public class ClientController {
    private ClientDAO model;
    private ClientView view;

    public ClientController(ClientDAO model, ClientView view) {
        this.model = model;
        this.view = view;
    }

    public void addClient(Client client) {
        model.addClient(client);
        view.displayAddClientMessage();
    }

    public void updateClient(Client client) {
        model.updateClient(client);
        view.displayUpdateClientMessage();
    }

    public List<Client> getClients() {
        return model.listAllClients();
    }

    public void listAllClients() {
        view.listAllClients(getClients());
    }
}