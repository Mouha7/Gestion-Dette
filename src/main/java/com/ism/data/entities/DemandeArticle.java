package com.ism.data.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class DemandeArticle {
    private int idDemandeArticle;
    private int qteArticle;
    private static int nbr;

    // Nav
    private Article article;
    private DemandeDette demandeDette;

    public DemandeArticle() {
        this.idDemandeArticle = ++nbr;
    }
}
