package com.ism.data.entities;

import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Article {
    private int idArticle;
    private String libelle;
    private Double prix;
    private Integer qteStock;
    private static int nbr;

    // Nav
    private List<Detail> details;
    private List<DemandeArticle> demandeArticles;

    public Article() {
        this.idArticle = ++nbr;
    }

    public void addDetail(Detail detail) {
        details.add(detail);
    }

    public void addDemandeArticle(DemandeArticle demandeArticle) {
        demandeArticles.add(demandeArticle);
    }
}
