package com.ism.data.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import com.ism.data.enums.EtatDemandeDette;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DemandeDette {
    private int idDemandeDette;
    private LocalDate dateDemande;
    private Double montantTotal;
    private EtatDemandeDette etat;
    private static int nbr;

    // Nav
    private List<DemandeArticle> demandeArticles = new ArrayList<>();
    private Dette dette;
    private Client client;

    public void addDemandeArticle(DemandeArticle demandeArticle) {
        demandeArticles.add(demandeArticle);
    }

    public DemandeDette() {
        this.idDemandeDette = ++nbr;
    }
}
