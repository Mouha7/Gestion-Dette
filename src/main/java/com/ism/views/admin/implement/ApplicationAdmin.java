package com.ism.views.admin.implement;

import java.util.Arrays;
import java.util.Scanner;

import com.ism.core.factory.IFactory;
import com.ism.core.factory.IFactoryService;
import com.ism.core.factory.IFactoryView;
import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.Dette;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDetteService;
import com.ism.services.IUserService;
import com.ism.views.IArticleView;
import com.ism.views.IClientView;
import com.ism.views.IDetteView;
import com.ism.views.IUserView;
import com.ism.views.admin.IApplicationAdmin;

public class ApplicationAdmin implements IApplicationAdmin {
    private static final String MSG_CHOICE = "Choisissez une option : ";
    private static final String MSG_CLIENT = "Aucun client n'a été enregistré.";
    private static final String MSG_ACCOUNT = "Compte créer avec succès !";
    private final IFactoryService factoryService;
    private final IFactoryView factoryView;
    private final Scanner scanner;

    public ApplicationAdmin(IFactory factory, Scanner scanner) {
        this.factoryService = factory.getFactoryService();
        this.factoryView = factory.getFactoryView();
        this.scanner = scanner;
        run();
    }

    @Override
    public void run() {
        int choix;
        do {
            choix = menu();
            scanner.nextLine();
            switch (choix) {
                case 0:
                    factoryService.getInstanceClientService().add(factoryView.getInstanceClientView().saisir());
                    msgSuccess(MSG_ACCOUNT);
                    break;
                case 1:
                    createAccountCustomer(factoryService.getInstanceClientService(), factoryView.getInstanceClientView(), factoryService.getInstanceUserService(), factoryView.getInstanceUserView());
                    break;
                case 2:
                    factoryService.getInstanceUserService().add(factoryView.getInstanceUserView().saisir());
                    msgSuccess(MSG_ACCOUNT);
                    break;
                case 3:
                    activeDesactiveAccount(factoryService.getInstanceUserService(), factoryView.getInstanceUserView());
                    break;
                case 4:
                    listingUserActifs(factoryService.getInstanceUserService(), factoryView.getInstanceUserView());
                    break;
                case 5:
                    createArticle(factoryService.getInstanceArticleService(), factoryView.getInstanceArticleView());
                    break;
                case 6:
                    listingArticleAvailable(factoryService.getInstanceArticleService(), factoryView.getInstanceArticleView());
                    break;
                case 7:
                    updateQte(factoryService.getInstanceArticleService(), factoryView.getInstanceArticleView());
                    break;
                case 8:
                    soldes(factoryService.getInstanceDetteService(), factoryView.getInstanceDette());
                    break;
                default:
                    System.out.println("Merci d'avoir utiliser notre application, au revoir !");
                    break;
            }
        } while (choix != 9);
    }

    @Override
    public int menu() {
        String choice;
        String[] values = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        do {
            System.out.println("1- Créer un compte à un client n’ayant pas de compte");
            System.out.println("2- Créer un compte utilisateur (Admin ou Boutiquier)");
            System.out.println("3- Activer/Désactiver un compte utilisateur");
            System.out.println("4- Lister les comptes utilisateurs actif par rôle");
            System.out.println("5- Créer un article");
            System.out.println("6- Lister les articles disponibles");
            System.out.println("7- Mettre à jour la quantité en stock d'un article");
            System.out.println("8- Archiver les dettes soldées");
            System.out.println("9- Quitter");
            System.out.print(MSG_CHOICE);
            choice = scanner.nextLine();
            if (Arrays.asList(values).contains(choice)) {
                System.out.println("Erreur, choix invalide.");
            }
        } while (Arrays.asList(values).contains(choice));
        return Integer.parseInt(choice);
    }
    
    public int status() {
        String choix;
        do {
            System.out.println("1- Compte utilisateur (Client)");
            System.out.println("2- Compte utilisateur (Admin ou Boutiquier)");
            System.out.print(MSG_CHOICE);
            choix = scanner.nextLine();
            if (!choix.equals("1") && !choix.equals("2")) {
                System.out.println("Erreur, choix invalide. Veuillez entrer 1 ou 2.");
            }
        } while (!choix.equals("1") && !choix.equals("2"));

        return Integer.parseInt(choix);
    }

    public boolean isEmpty(int size, String msg) {
        if (size == 0) {
            System.out.println(msg);
            return true;
        }
        return false;
    }

    public void msgStatus(boolean state) {
        if (state) {
            System.out.println("Activer avec succès");
        } else {
            System.out.println("Désactiver avec succès");
        }
    }

    public int role() {
        String choix;
        do {
            System.out.println("1- Admin");
            System.out.println("2- Boutiquier");
            System.out.println("3- Client");
            System.out.print(MSG_CHOICE);
            choix = scanner.nextLine();
            if (!choix.equals("1") && !choix.equals("2") && !choix.equals("3")) {
                System.out.println("Erreur, choix invalide. Veuillez entrer 1 ou 2 ou 3.");
            }
        } while (!choix.equals("1") && !choix.equals("2") && !choix.equals("3"));

        return Integer.parseInt(choix);
    }

    public void msgSuccess() {
        msgSuccess("Ajouté avec succès !");
    }

    public void msgSuccess(String msg) {
        System.out.println(msg);
    }

    public void soldes(IDetteService detteService, IDetteView detteView) {
        if (isEmpty(detteService.length(), "Aucun compte soldé n'a été enregistré.")) {
            return;
        }
        Dette dette = detteView.getObject(detteService.getAllSoldes());
        boolean state = !dette.isStatus();
        detteService.setStatus(dette, state);
        msgStatus(state);
    }

    public void updateQte(IArticleService articleService, IArticleView articleView) {
        if (isEmpty(articleService.length(), "Aucun article n'a été enregistré.")) {
            return;
        }
        Article article = articleView.getObject(articleService.findAll());
        Integer newQte = Integer.valueOf(articleView.checked("Entrez la nouvelle quantité de l'article : ", "la quantité").toString());
        articleService.setQte(article, newQte);
        msgSuccess("Modifiée avec succès !");
    }

    public void listingArticleAvailable(IArticleService articleService, IArticleView articleView) {
        if (isEmpty(articleService.length(), "Aucun article n'a été enregistré.")) {
            return;
        }
        articleView.afficher(articleService.findAllAvailable());
    }

    public void createArticle(IArticleService articleService, IArticleView articleView) {
        articleService.add(articleView.saisir());
        msgSuccess();
    }

    public void listingUserActifs(IUserService userService, IUserView userView) {
        int choixRole = role();
        switch (choixRole) {
            case 1:
                if (isEmpty(userService.length(), "Aucun admin n'a été enregistré.")) {
                    break;
                }
                if (isEmpty(userService.getAllActifs(0).size(), "Aucun admin n'est actif.")) {
                    break;
                }
                userView.afficher(userService.getAllActifs(0));
                break;
            case 2:
                if (isEmpty(userService.length(), "Aucun boutiquier n'a été enregistré.")) {
                    break;
                }
                if (isEmpty(userService.getAllActifs(1).size(), "Aucun boutiquier n'est actif.")) {
                    break;
                }
                userView.afficher(userService.getAllActifs(1));
                break;
            case 3:
                if (isEmpty(userService.length(), MSG_CLIENT)) {
                    break;
                }
                if (isEmpty(userService.getAllActifs(2).size(), "Aucun client n'est actif.")) {
                    break;
                }
                userView.afficher(userService.getAllActifs(2));
                break;
            default:
                break;
        }
    }

    public void activeDesactiveAccount(IUserService userService, IUserView userView) {
            if (isEmpty(userService.length(), "Aucun compte admin ou boutiquier ou client n'a été enregistré.")) {
                return;
            }
            User user = userView.getObject(userService.findAll());
            boolean state = !user.isStatus();
            userService.setStatus(user, state);
            msgStatus(state);
    }

    public void createAccountCustomer(IClientService clientService, IClientView clientView, IUserService userService, IUserView userView) {
        if (isEmpty(clientService.findAllCustomerAvailable().size(), MSG_CLIENT)) {
            return;
        }
        Client client = clientView.getObject(clientService.findAllCustomerAvailable());
        userService.add(userView.accountCustomer(client));
        msgSuccess(MSG_ACCOUNT);
    }

}
