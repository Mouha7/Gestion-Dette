package com.ism.data.entities;

import java.time.LocalDate;
import java.util.List;

import com.ism.data.enums.EtatDette;

import lombok.Data;

@Data
public class Dette {
    private int idDette;
    private LocalDate dateCreation;
    private Double montantTotal;
    private Double montantVerser;
    private Double montantRestant;
    private EtatDette etat;

    // Nav
    private Client client;
    private DemandeDette demandeDette;
    private List<Paiement> paiements;
    private List<Detail> details;

    public void addPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    public void addDetail(Detail detail) {
        details.add(detail);
    }
}
