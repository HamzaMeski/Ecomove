package controller;

import model.dao.GraphDAO;
import view.reservation.ReservationView;
import model.entities.Ticket;

import java.util.List;

public class ReservationController {
    private GraphDAO model;
    private ReservationView view;
    
    public ReservationController(GraphDAO model, ReservationView view) {
        this.model = model; 
        this.view = view;
    }

    public void searchJourney(String departure, String destination) {
        List<List<Ticket>> paths = model.searchJourney(departure, destination);
        view.searchJourney(paths);
    }

    public void reserveJourneyTickets() {

    }
}
