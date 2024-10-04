package com.ism.data.entities;

import java.util.List;
import lombok.Data;

@Data
public class Client {
    private int idClient;
    private String surname;
    private String tel;
    private String address;
    private Double cumulMontantDu;
    private boolean status;
    private static int nbr;

    // Navigabilité: revoir la pertinence de garder certain navigabilité 
    private User user;
    private List<DemandeDette> demandeDettes;
    private List<Dette> dettes;

    public Client() {
        this.idClient = ++nbr;
    }

    public void addDemandeDette(DemandeDette demandeDette) {
        demandeDettes.add(demandeDette);
    }

    public void addDetteClient(Dette dette) {
        dettes.add(dette);
    }
}
