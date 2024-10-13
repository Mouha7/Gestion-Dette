package com.ism.data.entities;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Paiement {
    private int idPaiement;
    private LocalDate datePaiement;
    private Double montantPaye;
    private static int nbr;

    // Nav
    private Dette dette;

    public Paiement() {
        this.idPaiement = ++nbr;
        this.datePaiement = LocalDate.now();
    }
}
