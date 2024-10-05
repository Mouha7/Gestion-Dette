package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Dette;
import com.ism.services.IDetteService;
import com.ism.views.IDetteView;

public class DetteView extends ImpView<Dette> implements IDetteView {
    private IDetteService detteService;

    public DetteView(IDetteService detteService) {
        this.detteService = detteService;
    }

    @Override
    public Dette saisir() {
        return null;
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
    
}
