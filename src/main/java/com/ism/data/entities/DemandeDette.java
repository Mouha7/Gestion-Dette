package com.ism.data.entities;

import java.time.LocalDate;
import java.util.List;

import com.ism.data.enums.EtatDemandeDette;

import lombok.Data;

@Data
public class DemandeDette {
    private int idDemandeDette;
    private LocalDate dateDemande;
    private Double montantTotal;
    private EtatDemandeDette etat;

    // Nav
    private List<DemandeArticle> demandeArticles;
    private Dette dette;
    private Client client;

    public void addDemandeArticle(DemandeArticle demandeArticle) {
        demandeArticles.add(demandeArticle);
    }
}
