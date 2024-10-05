package com.ism.views.implement;

import java.util.List;
import java.util.regex.Pattern;
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
        this.clientService = clientService;
        this.demandeArticleService = demandeArticleService;
        this.demandeDetteService = demandeDetteService;
        this.articleService = articleService;
    }

    @Override
    public DemandeDette saisir(User user) {
        Article article;
        String choice;  
        String qte;
        DemandeArticle demandeArticle;
        DemandeDette demandeDette = new DemandeDette();
        Client client = new Client();
        articleService.findAll();
        do {
            article = new Article();
            demandeArticle = new DemandeArticle();
            if (articleService.length() == 0) {
                System.out.println("Aucun article n'a été enregistré.");
                return null;
            }
            articleService.findAll().forEach(System.out::println);
            System.out.print("Entrez le libelle de l'article de la demande de dette(0 pour terminer): ");
            choice = scanner.nextLine();
            System.out.println("Entrez la quantité: ");
            qte = scanner.nextLine();
            article.setLibelle(choice);
            article = articleService.findBy(article, articleService.findAll());
            Pattern digitPattern = Pattern.compile("\\d+");
            if (!digitPattern.matcher(qte).matches()) {
                System.out.println("Erreur, la quantité est incorrecte.");
            } else if (!choice.equals("0")) {
                client.setUser(user);
                demandeDette.setMontantTotal(article.getPrix() *  Integer.parseInt(qte));
                demandeDette.setEtat(EtatDemandeDette.ENCOURS);
                demandeDette.setDateDemande(LocalDate.now());
                demandeDette.setClient(clientService.findBy(client));

                demandeArticle.setQteArticle(Integer.parseInt(qte));
                demandeArticle.setArticle(article);
                demandeArticle.setDemandeDette(demandeDette);
                
                demandeDette.addDemandeArticle(demandeArticle);

                // Add Nav
                demandeArticleService.add(demandeArticle);
            }
        } while (!choice.equals("0"));
        return demandeDette;
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
