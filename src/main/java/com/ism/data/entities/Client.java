package com.ism.data.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = {"demandeDettes", "dettes"})
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
        this.cumulMontantDu = 0.0;
        this.idClient = ++nbr;
    }

    public void addDemandeDette(DemandeDette demandeDette) {
        if (demandeDettes == null) {
            demandeDettes = new ArrayList<>();
        }
        demandeDettes.add(demandeDette);
    }

    public void addDetteClient(Dette dette) {
        if (dettes == null) {
            dettes = new ArrayList<>();
        }
        dettes.add(dette);
    }

    public Double getCumulMontantDu() {
        if (dettes != null && !dettes.isEmpty()) {
            for (Dette d : dettes) {
                cumulMontantDu += d.getMontantRestant();
            }
            return cumulMontantDu;
        }
        return cumulMontantDu;
    }
}
