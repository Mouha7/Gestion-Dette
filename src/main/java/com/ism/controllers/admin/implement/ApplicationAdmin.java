package com.ism.controllers.admin.implement;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.Scanner;

import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.Dette;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDetteService;
import com.ism.services.IUserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import com.ism.controllers.IArticleView;
import com.ism.controllers.IClientView;
import com.ism.controllers.IDetteView;
import com.ism.controllers.IUserView;
import com.ism.controllers.admin.IApplicationAdmin;
import com.ism.controllers.implement.Application;
import com.ism.core.config.router.Router;
import com.ism.core.factory.IFactory;
import com.ism.core.factory.implement.Factory;
import com.ism.core.helper.Success;
import com.ism.core.helper.Tools;

public class ApplicationAdmin extends Application implements IApplicationAdmin, Initializable {
    private IFactory factory = Factory.getInstance();
    private IArticleService articleService;
    private IArticleView articleView;
    private IClientService clientService;
    private IClientView clientView;
    private IDetteService detteService;
    private IDetteView detteView;
    private IUserService userService;
    private IUserView userView;
    private Scanner scanner;

    // FXML
    @FXML
    private Label infoConnect;
    @FXML
    private Label numAllProduct;
    @FXML
    private Label numActifUser;
    @FXML
    private Label numCustomer;
    @FXML
    private Label numSolde;
    @FXML
    private Stage stage;

    public ApplicationAdmin(Scanner scanner) {
        this.articleService = factory.getFactoryService().getInstanceArticleService();
        this.articleView = factory.getFactoryView().getInstanceArticleView();
        this.clientService = factory.getFactoryService().getInstanceClientService();
        this.clientView = factory.getFactoryView().getInstanceClientView();
        this.detteService = factory.getFactoryService().getInstanceDetteService();
        this.detteView = factory.getFactoryView().getInstanceDetteView();
        this.userService = factory.getFactoryService().getInstanceUserService();
        this.userView = factory.getFactoryView().getInstanceUserView();
        this.scanner = scanner;
    }

    public ApplicationAdmin() {
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        this.articleService = factory.getFactoryService().getInstanceArticleService();
        this.articleView = factory.getFactoryView().getInstanceArticleView();
        this.clientService = factory.getFactoryService().getInstanceClientService();
        this.clientView = factory.getFactoryView().getInstanceClientView();
        this.detteService = factory.getFactoryService().getInstanceDetteService();
        this.detteView = factory.getFactoryView().getInstanceDetteView();
        this.userService = factory.getFactoryService().getInstanceUserService();
        this.userView = factory.getFactoryView().getInstanceUserView();
        Success.showSuccessMsg("Message Connexion", "Vous êtes connecté avec succès.");
        // Ajouter des informations dans les labels
        if (infoConnect != null) {
            infoConnect.setText(Router.userParams);
        }
        // Mettre à jour les labels une fois les services initialisés
        updateLabels(); 
    }

    @FXML
    public void loadGestionClient(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Gestion Compte Client", "/com/ism/views/gestion.client.fxml");
        // Mettre à jour les labels une fois les services initialisés
        updateLabels(); 
    }

    @FXML
    public void loadGestionUser(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Gestion Compte Utilisateur", "/com/ism/views/gestion.user.fxml");
        // Mettre à jour les labels une fois les services initialisés
        updateLabels(); 
    }

    @FXML
    public void loadListAllUser(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Liste Compte Utilisateur", "/com/ism/views/list.users.fxml");
        // Mettre à jour les labels une fois les services initialisés
        updateLabels(); 
    }

    @FXML
    public void loadGestionArticle(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Gestion Article", "/com/ism/views/gestion.article.fxml");
        // Mettre à jour les labels une fois les services initialisés
        updateLabels(); 
    }

    @FXML
    public void loadArchiveDette(ActionEvent e) throws IOException {
        Tools.loadPersist(e, "Archive Dette", "/com/ism/views/archive.dette.fxml");
        // Mettre à jour les labels une fois les services initialisés
        updateLabels(); 
    }

    @Override
    @FXML
    public void logout(ActionEvent e) throws IOException {
        // Retourner à la page de connexion
        Tools.load(e, "Gestion Dette Boutiquier", "/com/ism/views/connexion.fxml");
    }

    private void updateLabels() {
        if (articleService != null && userService != null && 
            clientService != null && detteService != null) {
            numAllProduct.setText("Produits Disponibles (" + articleService.findAllAvailable().size() + ")");
            numActifUser.setText("Utilisateurs Actifs (" + userService.lengthUserActif() + ")");
            numCustomer.setText("Clients (" + clientService.length() + ")");
            numSolde.setText("Dettes Soldées (" + detteService.getAllSoldes().size() + ")");
        }
    }

    @Override
    public void run(User user) {
        Integer choix;
        msgWelcome(user);
        do {
            choix = menu();
            switch (choix) {
                case 1:
                    createAccountCustomer(clientService, clientView, userService, userView);
                    break;
                case 2:
                    createAccountUser(userService, userView);
                    break;
                case 3:
                    activeDesactiveAccount(userService, userView, user);
                    break;
                case 4:
                    listingUserActifs(userService, userView, user);
                    break;
                case 5:
                    createArticle(articleService, articleView);
                    break;
                case 6:
                    listingArticleAvailable(articleService, articleView);
                    break;
                case 7:
                    updateQte(articleService, articleView);
                    break;
                case 8:
                    soldes(detteService, detteView);
                    break;
                default:
                    System.out.println(MSG_EXIT);
                    break;
            }
        } while (choix != 9);
    }

    @Override
    public int menu() {
        String[] validValues = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };
        String choice;
        do {
            System.out.println("1- Créer un compte à un client n'ayant pas de compte");
            System.out.println("2- Créer un compte utilisateur (Admin ou Boutiquier)");
            System.out.println("3- Activer/Désactiver un compte utilisateur");
            System.out.println("4- Lister les comptes utilisateurs actif par rôle");
            System.out.println("5- Créer un article");
            System.out.println("6- Lister les articles disponibles");
            System.out.println("7- Mettre à jour la quantité en stock d'un article");
            System.out.println("8- Archiver les dettes soldées");
            System.out.println("9- Déconnexion");
            System.out.print(MSG_CHOICE);
            choice = scanner.nextLine();
            if (!Arrays.asList(validValues).contains(choice)) {
                System.out.println("Erreur, choix de l'index du menu invalide.");
            }
        } while (!Arrays.asList(validValues).contains(choice));
        return Integer.parseInt(choice);
    }

    @Override
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

    public void createAccountUser(IUserService userService, IUserView userView) {
        User user = userView.saisir(userService);
        if (user != null) {
            userService.add(user);
            msgSuccess(MSG_ACCOUNT);
        }
    }

    @Override
    public boolean isEmpty(int size, String msg) {
        if (size == 0) {
            System.out.println(msg);
            return true;
        }
        return false;
    }

    @Override
    public void msgStatus(boolean state) {
        if (state) {
            System.out.println("Activer avec succès");
        } else {
            System.out.println("Désactiver avec succès");
        }
    }

    @Override
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

    @Override
    public void msgSuccess() {
        msgSuccess("Ajouté avec succès !");
    }

    @Override
    public void msgSuccess(String msg) {
        System.out.println(msg);
    }

    @Override
    public void soldes(IDetteService detteService, IDetteView detteView) {
        if (isEmpty(detteService.getAllSoldes().size(), "Aucun dette soldé n'a été enregistré.")) {
            return;
        }
        Dette dette = detteView.getObject(detteService.getAllSoldes());
        boolean state = !dette.isStatus();
        dette.setStatus(state);
        detteService.update(dette);
        msgStatus(state);
    }

    @Override
    public void updateQte(IArticleService articleService, IArticleView articleView) {
        if (isEmpty(articleService.length(), "Aucun article n'a été enregistré.")) {
            return;
        }
        Article article = articleView.getObject(articleService.findAll());
        Integer newQte = 0;
        // Integer newQte = Integer.valueOf(articleView.checked("Entrez la nouvelle quantité de l'article : ", "la quantité").toString());
        article.setQteStock(newQte);
        articleService.update(article);
        msgSuccess("Modifiée avec succès !");
    }

    @Override
    public void listingArticleAvailable(IArticleService articleService, IArticleView articleView) {
        if (isEmpty(articleService.length(), "Aucun article n'a été enregistré.")) {
            return;
        }
        articleView.afficher(articleService.findAllAvailable());
    }

    @Override
    public void createArticle(IArticleService articleService, IArticleView articleView) {
        articleService.add(articleView.saisir());
        msgSuccess();
    }

    @Override
    public void listingUserActifs(IUserService userService, IUserView userView, User userConnect) {
        int choixRole = role();
        switch (choixRole) {
            case 1:
                if (isEmpty(userService.length(), "Aucun admin n'a été enregistré.")) {
                    break;
                }
                if (isEmpty(userService.getAllActifs(0, userConnect).size(), "Aucun admin n'est actif.")) {
                    break;
                }
                userView.afficher(userService.getAllActifs(0, userConnect));
                break;
            case 2:
                if (isEmpty(userService.length(), "Aucun boutiquier n'a été enregistré.")) {
                    break;
                }
                if (isEmpty(userService.getAllActifs(1, userConnect).size(), "Aucun boutiquier n'est actif.")) {
                    break;
                }
                userView.afficher(userService.getAllActifs(1, userConnect));
                break;
            case 3:
                if (isEmpty(userService.length(), MSG_CLIENT)) {
                    break;
                }
                if (isEmpty(userService.getAllActifs(2, userConnect).size(), "Aucun client n'est actif.")) {
                    break;
                }
                userView.afficher(userService.getAllActifs(2, userConnect));
                break;
            default:
                break;
        }
    }

    @Override
    public void activeDesactiveAccount(IUserService userService, IUserView userView, User userConnect) {
        List<User> users = userService.findAll()
                .stream()
                .filter(us -> !Objects.equals(us.getId(), userConnect.getId()))
                .collect(Collectors.toList());
        if (isEmpty(users.size(), "Aucun compte admin ou boutiquier ou client n'a été enregistré.")) {
            return;
        }
        User user = userView.getObject(users);
        user.setStatus(!user.isStatus());
        userService.update(users, user);
        msgStatus(user.isStatus());
    }

    @Override
    public void createAccountCustomer(IClientService clientService, IClientView clientView, IUserService userService,
            IUserView userView) {
        List<Client> clients = clientService.findAllCustomerAvailable();
        if (isEmpty(clients.size(), MSG_CLIENT)) {
            return;
        }
        Client client = clientView.getObject(clientService.findAllCustomerAvailable());
        User user = userView.accountCustomer(userService, client);
        if (user != null) {
            client.setUser(user);
            userService.add(user);
            clientService.update(clients, client);
            msgSuccess(MSG_ACCOUNT);
        }
    }
}
