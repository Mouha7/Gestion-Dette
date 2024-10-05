package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.Article;
import com.ism.data.repository.IArticleRepository;
import com.ism.services.IArticleService;

public class ArticleService implements IArticleService {
    private IArticleRepository articleRepository;

    public ArticleService(IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public boolean add(Article value) {
        return articleRepository.insert(value);    
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.selectAll();
    }

    @Override
    public void setQte(Article article, int newQte) {
        articleRepository.updateQte(article, newQte);
    }

    @Override
    public List<Article> findAllAvailable() {
        return articleRepository.selectAllAvailable();
    }

    @Override
    public int length() {
        return articleRepository.size();
    }

    @Override
    public Article findBy(Article article, List<Article> articles) {
        for (Article value : articles) {
            if (value.getIdArticle() == article.getIdArticle()) {
                return value;
            }
            if (value.getLibelle().compareTo(article.getLibelle()) == 0) {
                return value;
            }
        }
        return null;
    }
    
}
