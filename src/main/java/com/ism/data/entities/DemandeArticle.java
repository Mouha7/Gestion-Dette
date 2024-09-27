package com.ism.data.entities;

import lombok.Data;

@Data
public class DemandeArticle {
    private int idDemandeArticle;
    private int qteArticle;

    // Nav
    private Article article;
    private DemandeDette demandeDette;
}
