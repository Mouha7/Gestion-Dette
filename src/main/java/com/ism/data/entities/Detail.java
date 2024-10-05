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

    // Nav
    private Article article;
    private Dette dette;
}
