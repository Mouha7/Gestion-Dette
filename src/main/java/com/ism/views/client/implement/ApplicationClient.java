package com.ism.views.client.implement;

import java.util.Arrays;
import java.util.Scanner;

import com.ism.data.entities.Detail;
import com.ism.data.entities.Dette;
import com.ism.data.entities.User;
import com.ism.services.IDemandeDetteService;
import com.ism.services.IDetteService;
import com.ism.views.IDemandeDetteView;
import com.ism.views.IDetteView;
import com.ism.views.client.IApplicationClient;
import com.ism.views.implement.Application;

public class ApplicationClient extends Application implements IApplicationClient {
    private final IDemandeDetteService demandeDetteService;
    private final IDemandeDetteView demandeDetteView;
    private final IDetteService detteService;
    private final IDetteView detteView;
    private final Scanner scanner;

    public ApplicationClient(IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView, IDetteService detteService, IDetteView detteView, Scanner scanner) {
        this.demandeDetteService = demandeDetteService;
        this.demandeDetteView = demandeDetteView;
        this.detteService = detteService;
        this.detteView = detteView;
        this.scanner = scanner;
    }

    @Override
    public int menu() {
        String choice;
        String[] validValues = { "1", "2", "3", "4", "5" };
        do {
            System.out.println("1- Lister des dettes non soldées");
            System.out.println("2- Faire une demande de dette");
            System.out.println("3- Lister des demandes de dette");
            System.out.println("4- Relancer une demande de dette annuler");
            System.out.println("5- Déconnexion");
            System.out.print(MSG_CHOICE);
            choice = scanner.nextLine();
            if (!Arrays.asList(validValues).contains(choice)) {
                System.out.println("Erreur, choix de l'index du menu invalide.");
            }
        } while (!Arrays.asList(validValues).contains(choice));
        return Integer.parseInt(choice);
    }

    @Override
    public void run(User user) {
        Integer choix;
        do {
            choix = menu();
            switch (choix) {
                case 1:
                    displayDette(detteService, detteView);
                    break;
                case 2:
                    saisirDette(demandeDetteService, demandeDetteView, user);
                    break;
                default:
                    System.out.println(MSG_EXIT);
                    break;
            }
        } while (choix != 5);
    }

    @Override
    public void displayDette(IDetteService detteService, IDetteView detteView) {
        if (isEmpty(detteService.length(), "Aucun dette n'a été enregistré.")) {
            return;
        }
        System.out.println("Choisissez l'id pour plus de detail");
        System.out.println("--------------------");
        detteView.afficher(detteService.findAll());
        Dette dette = detteView.getObject(detteService.findAll());
        System.out.println("--------------------");
        subMenu(dette);
    }

    @Override
    public void displayPaiement(Dette dette) {
        dette.getPaiements().forEach(System.out::println);
    }

    @Override
    public void displayArticle(Dette dette) {
        for (Detail detail : dette.getDetails()) {
            System.out.println(detail.getArticle());
        }
    }

    @Override
    public void subMenu(Dette dette) {
        String choice;
        System.out.println("1- Voir les articles");
        System.out.println("2- Voir les paiements");
        do {
            System.out.print(MSG_CHOICE);
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                displayArticle(dette);
            } else if (choice.equals("2")) {
                displayPaiement(dette);
            } else {
                System.out.println("Erreur, choix invalide.");
            }
        } while (!choice.equals("1") || !choice.equals("2"));
    }

    @Override
    public void saisirDette(IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView, User user) {
        if (demandeDetteView.saisir(user) != null) {
            demandeDetteService.add(demandeDetteView.saisir(user));
            msgSuccess("Demande de dette ajoutée avec succès.");
        }
    }
}
