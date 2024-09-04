package controller;

import model.entities.Contract;
import model.dao.ContractDAO;
import view.contract.ContractView;

import java.util.List; 
import java.util.ArrayList; 

public class ContractController {
    private ContractDAO model;
    private ContractView view;

    public ContractController(ContractDAO model, ContractView view) {
        this.model = model;
        this.view = view;
    }
 
    public void addContract(Contract contract) {
        model.addContract(contract);
        view.displayAddContractMessage();
    }
     
    public void updateContract() {

    }

    public void deleteContract() {

    }

    public void displayContract() {

    }

    public void listAllContracts() {
        List<Contract> contracts = model.listAllContracts();
        view.listAllContracts(contracts);
    }
}
