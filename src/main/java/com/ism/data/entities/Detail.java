package com.ism.data.entities;

import lombok.Data;

@Data
public class Detail {
    private int idDetteArticle;
    private int qte;

    // Nav
    private Article article;
    private DemandeDette demandeDette;
}
