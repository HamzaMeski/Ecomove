package controller;

import model.entities.Contract;
import model.dao.ContractDAO;
import view.contract.ContractView;

import java.util.List; 
import java.util.ArrayList; 
import model.entities.Contract;

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
     
    public void updateContract(Contract contract) {
        System.out.println(contract);
        System.out.println("#### #### ####");
    }

    public void deleteContract() {

    }

    public void displayContract() {

    }

    public List<Contract> listAllContracts() {
        List<Contract> contracts = model.listAllContracts();
        view.listAllContracts(contracts);
        return contracts;
    }
}
