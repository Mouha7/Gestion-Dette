package com.ism.data.entities;

import java.util.List;

import lombok.Data;

@Data
public class Article {
    private int idArticle;
    private String libelle;
    private Double prix;
    private Integer qteStock;

    // Nav
    private List<Detail> details;
    private List<DemandeArticle> demandeArticles;

    public void addDetail(Detail detail) {
        details.add(detail);
    }

    public void addDemandeArticle(DemandeArticle demandeArticle) {
        demandeArticles.add(demandeArticle);
    }
}
