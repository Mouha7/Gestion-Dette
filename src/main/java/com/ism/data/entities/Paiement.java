package com.ism.data.entities;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Paiement {
    private int idPaiement;
    private LocalDate datePaiement;
    private Double montantPaye;

    // Nav
    private Dette dette;
}
