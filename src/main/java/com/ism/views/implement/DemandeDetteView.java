package com.ism.views.implement;

import java.util.List;
import java.time.LocalDate;

import com.ism.data.entities.Article;
import com.ism.data.entities.Client;
import com.ism.data.entities.DemandeArticle;
import com.ism.data.entities.DemandeDette;
import com.ism.data.entities.User;
import com.ism.data.enums.EtatDemandeDette;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDemandeArticleService;
import com.ism.services.IDemandeDetteService;
import com.ism.views.IDemandeDetteView;

public class DemandeDetteView extends ImpView<DemandeDette> implements IDemandeDetteView {
    private IClientService clientService;
    private IDemandeArticleService demandeArticleService;
    private IDemandeDetteService demandeDetteService;
    private IArticleService articleService;

    public DemandeDetteView(IDemandeDetteService demandeDetteService, IArticleService articleService, IDemandeArticleService demandeArticleService, IClientService clientService) {
        this.articleService = articleService;
        this.clientService = clientService;
        this.demandeArticleService = demandeArticleService;
        this.demandeDetteService = demandeDetteService;
    }

    @Override
    public DemandeDette saisir(IArticleService articleService, User user) {
        List<Article> articleAvailable = articleService.findAllAvailable();
        if (articleAvailable.isEmpty()) {
            System.out.println("Aucun article n'a été enregistré.");
            return null;
        }
        DemandeDette demandeDette = initializeDemandeDette(user);
        String choice;
        do {
            displayAvailableArticles(articleAvailable);
            choice = getUserChoice();
            if (!choice.equals("0")) {
                processArticleChoice(choice, articleAvailable, demandeDette);
            }
        } while (!choice.equals("0"));
        return demandeDette;
    }

    private DemandeDette initializeDemandeDette(User user) {
        DemandeDette demandeDette = new DemandeDette();
        Client client = new Client();
        client.setUser(user);
        demandeDette.setClient(clientService.findBy(client));
        demandeDette.setEtat(EtatDemandeDette.ANNULE); // Pour les testes du 4
        demandeDette.setDateDemande(LocalDate.now());
        return demandeDette;
    }

    private void displayAvailableArticles(List<Article> articleAvailable) {
        articleAvailable.forEach(System.out::println);
    }

    private String getUserChoice() {
        System.out.print("Entrez le libelle de l'article de la demande de dette(0 pour terminer): ");
        return scanner.nextLine();
    }

    private void processArticleChoice(String choice, List<Article> articleAvailable, DemandeDette demandeDette) {
        int quantity = getValidQuantity();
        if (quantity == -1) return;

        Article article = findArticle(choice, articleAvailable);
        if (article == null) return;

        if (!checkStock(article, quantity)) return;

        updateArticleStock(article, quantity);
        addDemandeArticle(article, quantity, demandeDette);
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

    private Article findArticle(String libelle, List<Article> articleAvailable) {
        Article article = new Article();
        article.setLibelle(libelle);
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

    private void updateArticleStock(Article article, int quantity) {
        article.setQteStock(article.getQteStock() - quantity);
    }

    private void addDemandeArticle(Article article, int quantity, DemandeDette demandeDette) {
        DemandeArticle demandeArticle = new DemandeArticle();
        demandeArticle.setQteArticle(quantity);
        demandeArticle.setArticle(article);
        demandeArticle.setDemandeDette(demandeDette);
        
        demandeDette.addDemandeArticle(demandeArticle);
        demandeDette.setMontantTotal(demandeDette.getMontantTotal() + (article.getPrix() * quantity));
        
        demandeArticleService.add(demandeArticle);
    }

    @Override
    public DemandeDette getObject(List<DemandeDette> list) {
        DemandeDette demandeDette;
        int choix;
        int count = list.size();
        this.afficher(list);
        do {
            demandeDette = new DemandeDette();
            System.out.print("Choisissez une demande de dette par son id: ");
            choix = scanner.nextInt();
            demandeDette.setIdDemandeDette(choix);
            demandeDette = demandeDetteService.findBy(demandeDette);
            System.out.println(count);
            if (demandeDette == null || choix > count) {
                System.out.println("Erreur, l'id est invalide.");
            }
        } while (demandeDette == null || choix > count);
        return demandeDette;
    }

    @Override
    public DemandeDette saisir() {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saisir'");
    }
    
}
