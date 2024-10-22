package com.ism.data.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ism.data.enums.EtatDette;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Dette {
    private int idDette;
    private Double montantTotal;
    private Double montantVerser;
    private boolean status;
    private EtatDette etat;
    private LocalDate dateCreation;
    private static int nbr;

    // Nav
    private Client client;
    private DemandeDette demandeDette;
    private List<Paiement> paiements;
    private List<Detail> details;

    public Dette() {
        this.idDette = ++nbr;
        this.dateCreation = LocalDate.now();
        this.montantTotal = 0.0;
        this.montantVerser = 0.0;
    }

    public void addPaiement(Paiement paiement) {
        if (paiements == null) {
            paiements = new ArrayList<>();
        }
        paiements.add(paiement);
    }

    public void addDetail(Detail detail) {
        if (details == null) {
            details = new ArrayList<>();
        }
        details.add(detail);
    }

    public Double getMontantRestant() {
        return this.montantTotal - this.montantVerser;
    }
}
