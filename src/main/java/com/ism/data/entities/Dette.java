package com.ism.data.entities;

import java.time.LocalDate;
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
    private Double montantRestant;
    private boolean status;
    private EtatDette etat;
    private LocalDate dateCreation;
    private static int nbr;

    // Nav
    private Client client;
    private DemandeDette demandeDette; // Pas pertinent pour le moment pour la navigabilité
    private List<Paiement> paiements;
    private List<Detail> details;

    public Dette() {
        this.idDette = ++nbr;
        this.dateCreation = LocalDate.now();
        this.montantTotal = 0.0;
        this.montantVerser = 0.0;
        this.montantRestant = this.montantTotal - this.montantVerser;
    }

    public void addPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    public void addDetail(Detail detail) {
        details.add(detail);
    }
}
