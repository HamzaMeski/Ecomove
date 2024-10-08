package controller;

import model.entities.Contract;
import model.dao.ContractDAO;
import view.contract.ContractView;

import java.util.List; 

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
        model.updateContract(contract);
        view.displayUpdateContractMessage();
    }

    public List<Contract> listAllContracts() {
        List<Contract> contracts = model.listAllContracts();
        List<Contract> partnerContracts = view.listAllContracts(contracts);
        return partnerContracts;
    }
}