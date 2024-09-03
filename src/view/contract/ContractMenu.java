package view.contract;

import controller.ContractController;
import model.dao.ContractDAO;
import model.entities.Contract;
import model.enums.ContractStatus;
import lib.ScanInput;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.List;
import java.util.ArrayList; 

public class ContractMenu {
    ContractDAO model = new ContractDAO();
    ContractView view = new ContractView();
    

    public static void displayMenu() {
        System.out.println("display menu:");
    }
}
