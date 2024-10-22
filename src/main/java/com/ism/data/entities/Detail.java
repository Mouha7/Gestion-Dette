package com.ism.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Detail {
    private int idDetteArticle;
    private int qte;
    private Double prixVente;
    private static int nbr;

    // Nav
    private Article article;
    private Dette dette;

    public Detail() {
        this.idDetteArticle = ++nbr;
        this.qte = 0;
        this.prixVente = 0.0;
    }
}
