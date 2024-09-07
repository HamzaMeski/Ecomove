package controller; 
import view.ticket.TicketView;
import model.entities.Ticket;
import model.dao.TicketDAO;
import java.util.List;

public class TicketController {
    private TicketView view;
    private TicketDAO model;

    public TicketController(TicketDAO model, TicketView view) {
        this.view = view;
        this.model = model;
    }

    public void addTicket(Ticket ticket) {
        model.addTicket(ticket);
        view.displayAddTicketMessage();
    }

    public void updateTicket(Ticket ticket) {
        model.updateTicket(ticket);
        view.displayUpdateTicketMessage();
    }

    public List<Ticket> listAllTickets() {
        return model.listAllTickets();
    }
}
