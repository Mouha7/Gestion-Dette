package com.ism.views.client.implement;

import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.stream.Collectors;

import com.ism.data.entities.DemandeDette;
import com.ism.data.entities.Detail;
import com.ism.data.entities.Dette;
import com.ism.data.entities.User;
import com.ism.data.enums.EtatDemandeDette;
import com.ism.services.IArticleService;
import com.ism.services.IDemandeDetteService;
import com.ism.services.IDetteService;
import com.ism.views.IDemandeDetteView;
import com.ism.views.IDetteView;
import com.ism.views.client.IApplicationClient;
import com.ism.views.implement.Application;

public class ApplicationClient extends Application implements IApplicationClient {
    private final IArticleService articleService;
    private final IDemandeDetteService demandeDetteService;
    private final IDemandeDetteView demandeDetteView;
    private final IDetteService detteService;
    private final IDetteView detteView;
    private final Scanner scanner;

    public ApplicationClient(IArticleService articleService, IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView, IDetteService detteService, IDetteView detteView, Scanner scanner) {
        this.articleService = articleService;
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
                    saisirDette(articleService, demandeDetteService, demandeDetteView, user);
                    break;
                case 3:
                    displayDemandeDette(demandeDetteService, demandeDetteView);
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
        splice();
        detteView.afficher(detteService.findAll());
        Dette dette = detteView.getObject(detteService.findAll());
        splice();
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
    public void saisirDette(IArticleService articleService, IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView, User user) {
        if (demandeDetteView.saisir(articleService, user) != null) {
            demandeDetteService.add(demandeDetteView.saisir(articleService, user));
            msgSuccess("Demande de dette ajoutée avec succès.");
        }
    }

    @Override
    public void displayDemandeDette(IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView) {
        if (isEmpty(demandeDetteService.length(), "Aucune demande de dette n'a été enregistrée.")) {
            return;
        }
        demandeDetteView.afficher(demandeDetteService.findAll());
        splice();
        subMenuDemandeDette(demandeDetteService, demandeDetteView);
    }

    @Override
    public void subMenuDemandeDette(IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView) {
        String choice;
        System.out.println("Filtrer par: ");
        System.out.println("1- En cour la demande");
        System.out.println("2- Annuler la demande");
        do {
            System.out.print(MSG_CHOICE);
            choice = scanner.nextLine();
            if (choice.equals("1")) {
                List<DemandeDette> demandeDettes = demandeDetteService.findAll().stream().filter(dette -> dette.getEtat().name().compareTo("ENCOURS") == 0).collect(Collectors.toList());
                demandeDetteView.afficher(demandeDettes);
            } else if (choice.equals("2")) {
                List<DemandeDette> demandeDettes = demandeDetteService.findAll().stream().filter(dette -> dette.getEtat().name().compareTo("ANNULE") == 0).collect(Collectors.toList());
                demandeDetteView.afficher(demandeDettes);
            } else {
                System.out.println("Erreur, choix invalide.");
            }
        } while (!choice.equals("1") || !choice.equals("2"));
    }

    public void relaunchDette(IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView) {
        List<DemandeDette> demandeDettes = demandeDetteService.findAll().stream().filter(dette -> dette.getEtat().name().compareTo("ANNULE") == 0).collect(Collectors.toList());
        if (isEmpty(demandeDettes.size(), "Aucune demande de dette annulée n'a été trouvée.")) {
            return;
        }
        demandeDetteView.afficher(demandeDettes);
        splice();
        DemandeDette demandeDette = demandeDetteView.getObject(demandeDettes);
        demandeDette.setEtat(EtatDemandeDette.ENCOURS);
        demandeDetteService.update(demandeDette);
        msgSuccess("Relancement de la demande de dette avec succès.");
    }
}
