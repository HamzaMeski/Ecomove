package controller;
import model.dao.ClientDAO;
 
public class AuthenticationController {
    private ClientDAO model; 

    public AuthenticationController(ClientDAO model) {
        this.model = model;
    }

    public Integer checkAccountInfo(String userName, String email) {
        return model.checkAccountInfo(userName, email); // if the infos are correct It will return the ID of client something else it will return null;
    }
}
