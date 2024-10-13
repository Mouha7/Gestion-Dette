package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Paiement;
import com.ism.services.IPaiementService;
import com.ism.views.IPaiementView;

public class PaiementView extends ImpView<Paiement> implements IPaiementView {
    private IPaiementService paiementService;

    public PaiementView(IPaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @Override
    public Paiement saisir() {
        Paiement paiement = new Paiement();
        paiement.setMontantPaye(checkMontant("Entrer le montant du paiement: "));
        return paiement;
    }

    @Override
    public Paiement getObject(List<Paiement> list) {
        Paiement paiement;
        String choix;
        int count = list.size();
        this.afficher(list);
        do {
            paiement = new Paiement();
            System.out.print("Choisissez une paiement par son id: ");
            choix = scanner.nextLine();
            if (isInteger(choix)) {
                paiement.setIdPaiement(Integer.parseInt(choix));
                paiement = paiementService.findBy(paiementService.findAll(), paiement);
            } else {
                continue;
            }
            if (paiement == null || Integer.parseInt(choix) > count) {
                System.out.println("Erreur, l'id est invalide.");
            }

        } while (paiement == null);
        return paiement;
    }
    

    private Double checkMontant(String msg) {
        String montant;
        do {
            System.out.print(msg);
            montant = scanner.nextLine();
            if (!isDecimal(montant)) {
                System.out.println("Format incorrect, le montant doit être un nombre.");
            }
            if (Double.parseDouble(montant) <= 0.0) {
                System.out.println("Format incorrect, le montant doit positif.");
            }
        } while (!isDecimal(montant) && Double.parseDouble(montant) <= 0.0);
        return Double.parseDouble(montant);
    }
}
