package com.ism.data.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.Article;
import com.ism.data.repository.IArticleRepository;

public class ArticleRepository extends Repository<Article> implements IArticleRepository {
    @Override
    public void updateQte(Article article, int newQte) {
        Article art = selectBy(article);
        if (art != null) {
            art.setQteStock(newQte);
        } else {
            throw new IllegalArgumentException("Article not found");
        }
    }

    @Override
    public List<Article> selectAllAvailable() {
        return selectAll().stream()
                .filter(article -> article.getQteStock() != 0)
                .collect(Collectors.toList());
    }
}
