package com.ism.data.entities;

import lombok.Data;

@Data
public class Detail {
    private int idDetteArticle;
    private int qte;
    private Double prixVente;

    // Nav
    private Article article;
    private DemandeDette demandeDette;
}
