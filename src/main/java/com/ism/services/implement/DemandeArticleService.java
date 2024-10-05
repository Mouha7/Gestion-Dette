package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.DemandeArticle;
import com.ism.data.repository.IDemandeArticleRepository;
import com.ism.services.IDemandeArticleService;

public class DemandeArticleService implements IDemandeArticleService {
    private IDemandeArticleRepository demandeArticleRepository;

    public DemandeArticleService(IDemandeArticleRepository demandeArticleRepository) {
        this.demandeArticleRepository = demandeArticleRepository;
    }

    @Override
    public boolean add(DemandeArticle value) {
        return demandeArticleRepository.insert(value);
    }

    @Override
    public List<DemandeArticle> findAll() {
        return demandeArticleRepository.selectAll();
    }

    @Override
    public DemandeArticle findBy(DemandeArticle demandeArticle) {
        for (DemandeArticle article : findAll()) {
            if (article.getIdDemandeArticle() == demandeArticle.getIdDemandeArticle()) {
                return article;
            }
        }
        return null;
    }

    @Override
    public int length() {
        return demandeArticleRepository.size();
    }
    
}
