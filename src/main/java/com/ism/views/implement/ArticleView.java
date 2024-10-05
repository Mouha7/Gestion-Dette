package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Article;
import com.ism.services.IArticleService;
import com.ism.views.IArticleView;

public class ArticleView extends ImpView<Article> implements IArticleView {
    private IArticleService articleService;

    public ArticleView(IArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public Article saisir() {
        Article article = new Article();
        System.out.print("Entrez le libelle de l'article : ");
        article.setLibelle(scanner.nextLine());
        article.setPrix(Double.valueOf(checked("Entrez le prix de l'article : ", "le prix").toString()));
        article.setQteStock(Integer.valueOf(checked("Entrez la quantité de l'article : ", "la quantité").toString()));
        return article;
    }

    @Override
    public Integer checked(String msg, String msgError) {
        Integer value;
        do {
            System.out.print(msg);
            value = scanner.nextInt();
            if (value == 0) {
                System.out.println("Erreur, la valeur ne peux pas être 0.");
            } else if (value < 0) {
                System.out.println("Erreur, " + msgError + " ne peut être négatif.");
            }
        } while (value <= 0);
        return value;
    }

    @Override
    public Article getObject(List<Article> articles) {
        Article article;
        int choix;
        int count = articles.size();
        this.afficher(articles);
        do {
            article = new Article();
            System.out.print("Choisissez un article par son id: ");
            choix = scanner.nextInt();
            article.setIdArticle(choix);
            article = articleService.findBy(article, articles);
            System.out.println(article);
            if (article == null || choix > count) {
                System.out.println("Erreur, l'id de l'article est invalide.");
            }
        } while (article == null || choix > count);
        return article;
    }
}
