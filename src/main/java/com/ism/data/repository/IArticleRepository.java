package com.ism.data.repository;

import java.util.List;

import com.ism.core.repository.IRepository;
import com.ism.data.entities.Article;

public interface IArticleRepository extends IRepository<Article> {
    void updateQte(Article article, int newQte);
    List<Article> selectAllAvailable();
}
