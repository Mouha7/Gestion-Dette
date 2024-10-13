package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Dette;
import com.ism.data.enums.EtatDette;
import com.ism.services.IDetteService;
import com.ism.views.IDetteView;

public class DetteView extends ImpView<Dette> implements IDetteView {
    private IDetteService detteService;

    public DetteView(IDetteService detteService) {
        this.detteService = detteService;
    }

    @Override
    public Dette saisir() {
        Dette dette = new Dette();
        dette.setMontantTotal(checkMontant("Entrez le montant total: "));
        dette.setMontantVerser(checkMontant("Entrez le montant verser: "));
        dette.setStatus(true);
        dette.setEtat(EtatDette.ENCOURS);
        return dette;
    }

    @Override
    public Dette getObject(List<Dette> dettes) {
        Dette dette;
        int choix;
        int count = dettes.size();
        this.afficher(dettes);
        do {
            dette = new Dette();
            System.out.print("Choisissez une dette par son id: ");
            choix = scanner.nextInt();
            dette.setIdDette(choix);
            dette = detteService.findBy(dette);
            if (dette == null || choix > count) {
                System.out.println("Erreur, l'id est invalide.");
            }
        } while (dette == null || choix > count);
        return dette;
    }
    
    private Double checkMontant(String msg) {
        String montant;
        do {
            System.out.print(msg);
            montant = scanner.nextLine();
            if (!isDecimal(montant)) {
                System.out.println("Format incorrect, le montant doit être un nombre.");
            }
        } while (!isDecimal(montant));
        return Double.parseDouble(montant);
    }
}
