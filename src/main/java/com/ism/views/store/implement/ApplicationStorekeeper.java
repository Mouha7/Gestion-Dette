package com.ism.views.store.implement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Scanner;

import com.ism.data.entities.DemandeDette;
import com.ism.data.entities.Detail;
import com.ism.data.entities.Dette;
import com.ism.data.entities.Paiement;
import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDetteService;
import com.ism.services.IPaiementService;
import com.ism.services.IUserService;
import com.ism.views.IArticleView;
import com.ism.views.IClientView;
import com.ism.views.IDetteView;
import com.ism.views.IPaiementView;
import com.ism.views.IUserView;
import com.ism.views.implement.Application;
import com.ism.views.store.IApplicationStorekeeper;

public class ApplicationStorekeeper extends Application implements IApplicationStorekeeper {
    private final IArticleService articleService;
    private final IArticleView articleView;
    private final IClientService clientService;
    private final IClientView clientView;
    private final IUserService userService;
    private final IUserView userView;
    private final IDetteService detteService;
    private final IDetteView detteView;
    private final IPaiementService paiementService;
    private final IPaiementView paiementView;

    private final Scanner scanner;

    public ApplicationStorekeeper(IArticleService articleService, IArticleView articleView,
            IClientService clientService, IClientView clientView, IUserService userService,
            IUserView userView, IDetteService detteService, IDetteView detteView, IPaiementService paiementService,
            IPaiementView paiementView, Scanner scanner) {
        this.articleService = articleService;
        this.articleView = articleView;
        this.clientService = clientService;
        this.clientView = clientView;
        this.userService = userService;
        this.userView = userView;
        this.detteService = detteService;
        this.detteView = detteView;
        this.paiementService = paiementService;
        this.paiementView = paiementView;
        this.scanner = scanner;
    }

    @Override
    public int menu() {
        String choice;
        String[] validValues = { "1", "2", "3", "4", "5", "6", "7", "8" };
        do {
            System.out.println("1- Créer un client");
            System.out.println("2- Lister les clients");
            System.out.println("3- Rechercher un client");
            System.out.println("4- Créer une dette");
            System.out.println("5- Créer une paiement");
            System.out.println("6- Lister les dettes non soldées");
            System.out.println("7- Lister les demandes de dette en cours");
            System.out.println("8- Déconnexion");
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
                    saisirClient();
                    break;
                case 2:
                    displayClient(clientService, clientView);
                    break;
                case 3:
                    searchClientByTel(clientService, clientView);
                    break;
                case 4:
                    saisirDette(articleService, clientService, clientView, detteView, detteService, paiementView);
                    break;
                case 5:
                    saisirPaiement(paiementService, paiementView, detteService, detteView);
                    break;
                case 6:
                    break;
                default:
                    break;
            }
        } while (choix != 10);
    }

    public void saisirClient() {
        clientService.add(clientView.saisir());
        msgSuccess(MSG_ACCOUNT);
    }

    public void displayClient(IClientService clientService, IClientView clientView) {
        if (isEmpty(clientService.length(), MSG_CLIENT)) {
            return;
        }
        clientView.afficher(clientService.findAll());
        System.out.print("Voulez-vous filtrer les clients avec compte ou sans compte(O/N): ");
        char choix = scanner.nextLine().charAt(0);
        if (choix == 'O' || choix == 'o') {
            subMenuClient(clientService, clientView);
        }
        splice();
    }

    @Override
    public void subMenuClient(IClientService clientService, IClientView clientView) {
        String choice;
        System.out.println("Filtrer par: ");
        System.out.println("1- Un compte");
        System.out.println("2- Pas de compte");
        System.out.print(MSG_CHOICE);
        choice = scanner.nextLine();
        if (choice.equals("1")) {
            List<Client> clients = clientService.findAll().stream()
                    .filter(cl -> cl.getUser() == null).collect(Collectors.toList());
            clientView.afficher(clients);
        } else if (choice.equals("2")) {
            List<Client> clients = clientService.findAll().stream()
                    .filter(cl -> cl.getUser() != null).collect(Collectors.toList());
            clientView.afficher(clients);
        } else {
            System.out.println("Erreur, choix invalide.");
        }
    }

    public void searchClientByTel(IClientService clientService, IClientView clientView) {
        Client clientSearch = new Client();
        clientView.afficher(clientService.findAll());
        System.out.println("Entrer le tel du client à rechercher: ");
        clientSearch.setTel(scanner.nextLine());
        Client client = clientService.findBy(clientService.findAll(), clientSearch);
        displayClient(client);
        splice();
    }

    private void displayClient(Client client) {
        if (client == null) {
            System.out.println("Aucun client trouvé.");
            return;
        }
        System.out.println("ID : " + client.getIdClient());
        System.out.println("Surname : " + client.getSurname());
        System.out.println("Tel : " + client.getTel());
        System.out.println("Adresse : " + client.getAddress());
        System.out
                .println("Cumul Montant Dû : " + client.getCumulMontantDu() == null ? "0" : client.getCumulMontantDu());
        System.out.println("Status : " + client.isStatus());
        System.out.println("User : " + client.getUser() == null ? "N/A" : client.getUser());
        if (client.getDemandeDettes().isEmpty()) {
            splice();
            System.out.println("Liste Demande de dette : ");
            for (DemandeDette dette : client.getDemandeDettes()) {
                System.out.println("Montant Total: " + dette.getMontantTotal());
                System.out.println("Date: " + dette.getDateDemande());
                System.out.println("Etat: " + dette.getEtat());
            }
        } else {
            System.out.println("Liste des demandes de dette : N/A");
        }
        if (client.getDettes().isEmpty()) {
            splice();
            System.out.println("Liste Demande de dette : ");
            for (Dette dette : client.getDettes()) {
                System.out.println("Montant Total: " + dette.getMontantTotal());
                System.out.println("Montant Verser: " + dette.getMontantVerser());
                System.out.println("Montant Restant: " + dette.getMontantRestant());
                System.out.println("Status: " + dette.isStatus());
                System.out.println("État: " + dette.getEtat());
                System.out.println("Date: " + dette.getDateCreation());
            }
        } else {
            System.out.println("Liste des dettes : N/A");
        }
    }

    public void saisirDette(IArticleService articleService, IClientService clientService,
            IClientView clientView, IDetteView detteView, IDetteService detteService, IPaiementView paiementView) {
        List<Article> articleAvailable = articleService.findAllAvailable();
        List<Client> clients = clientService.findAll();
        if (articleAvailable.isEmpty()) {
            System.out.println("Aucun article n'a été enregistré.");
            return;
        }
        if (clients.isEmpty()) {
            System.out.println("Aucun client n'a été enregistré.");
            return;
        }

        Client client = new Client();
        clientView.afficher(clientService.findAll()); // À refaire avec findBy
        System.out.println("Entrer l'ID du client: "); //
        String idClient = scanner.nextLine(); //
        if (isInteger(idClient)) {
            client.setIdClient(Integer.parseInt(idClient));
            client = clientService.findBy(clients, client);
            Dette dette = detteView.saisir();
            dette.setClient(client);

            String choice;
            do {
                displayAvailableArticles(articleAvailable);
                choice = getUserChoice();
                if (!choice.equals("0")) {
                    processArticleChoice(articleService, choice, articleAvailable, dette);
                }
            } while (!choice.equals("0"));
            System.out.println("Voulez-vous enregistrer un(des) paiement(s)");
            char choicePay = scanner.nextLine().charAt(0);
            if (choicePay == 'O' || choicePay == 'o') {
                Paiement paiement = getPaiementClient(paiementView, dette);
                dette.addPaiement(paiement);
            }
            client.addDetteClient(dette);
            detteService.add(dette);
        } else {
            System.out.println("Erreur, l'ID du client est incorrect.");
        }
    }

    private Paiement getPaiementClient(IPaiementView paiementView, Dette dette) {
        Paiement paiement;
        do {
            paiement = paiementView.saisir();
            paiement.setDette(dette);
            if (paiement.getMontantPaye() > dette.getMontantTotal()) {
                System.out.println("Erreur, le montant payé dépasse le montant total de la dette.");
            }
        } while (paiement.getMontantPaye() > dette.getMontantTotal());
        return paiement;
    }

    private void displayAvailableArticles(List<Article> articleAvailable) {
        articleAvailable.forEach(System.out::println);
    }

    private String getUserChoice() {
        System.out.print("Entrez l'id de l'article(0 pour terminer): ");
        return scanner.nextLine();
    }

    private void processArticleChoice(IArticleService articleService, String choice, List<Article> articleAvailable,
            Dette dette) {
        int quantity = getValidQuantity();
        if (quantity <= -1)
            return;

        Article article = findArticle(choice, articleAvailable);
        if (article == null)
            return;

        if (!checkStock(article, quantity))
            return;

        updateArticleStock(articleService, article, quantity);
        addDemandeArticle(article, quantity, dette);
    }

    private int getValidQuantity() {
        System.out.print("Entrez la quantité: ");
        String qte = scanner.nextLine();

        if (!qte.matches("\\d+")) {
            System.out.println("Erreur, la quantité est incorrecte.");
            return -1;
        }

        return Integer.parseInt(qte);
    }

    private Article findArticle(String id, List<Article> articleAvailable) {
        Article article = new Article();
        if (id.matches("\\d")) {
            article.setIdArticle(Integer.parseInt(id));
        }
        Article foundArticle = articleService.findBy(article, articleAvailable);

        if (foundArticle == null) {
            System.out.println("Article non trouvé.");
        }

        return foundArticle;
    }

    private boolean checkStock(Article article, int quantity) {
        if (article.getQteStock() < quantity) {
            System.out.println("Quantité insuffisante en stock.");
            return false;
        }
        return true;
    }

    private void updateArticleStock(IArticleService articleService, Article article, int quantity) {
        for (Article ar : articleService.findAllAvailable()) {
            if (ar.getIdArticle() == article.getIdArticle()) {
                ar.setQteStock(ar.getQteStock() - quantity);
                return;
            }
        }
    }

    private void addDemandeArticle(Article article, int quantity, Dette dette) {
        Detail detail = new Detail();
        detail.setQte(quantity);
        detail.setPrixVente(article.getPrix());
        detail.setArticle(article);
        detail.setDette(dette);
    }

    public void saisirPaiement(IPaiementService paiementService, IPaiementView paiementView, IDetteService detteService, IDetteView detteView) {
        if (detteService.findAll().isEmpty()) {
            System.out.println("Aucune dette n'a été enregistré.");
            return;
        }

        List<Dette> dettes = detteService.findAll();
        Dette dette = detteView.getObject(dettes);
        Paiement paiement = getPaiementClient(paiementView, dette);
        Double sum = dette.getMontantVerser() + paiement.getMontantPaye();
        dette.setMontantVerser(sum);
        detteService.update(dettes, dette);
        paiementService.add(paiement);
        msgSuccess("Paiement effectué avec succès!");
    }
}
