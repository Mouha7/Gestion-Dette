package com.ism.services.implement;

import java.util.List;
import java.util.Objects;

import com.ism.data.entities.DemandeArticle;
import com.ism.data.repository.IDemandeArticleRepository;
import com.ism.services.IDemandeArticleService;

public class DemandeArticleService implements IDemandeArticleService {
    private IDemandeArticleRepository demandeArticleRepository;

    public DemandeArticleService(IDemandeArticleRepository demandeArticleRepository) {
        this.demandeArticleRepository = demandeArticleRepository;
    }

    @Override
    public DemandeArticle add(DemandeArticle value) {
        return demandeArticleRepository.insert(value);
    }

    @Override
    public List<DemandeArticle> findAll() {
        return demandeArticleRepository.selectAll();
    }

    @Override
    public DemandeArticle findBy(DemandeArticle demandeArticle) {
        for (DemandeArticle article : findAll()) {
            if (Objects.equals(article.getId(), demandeArticle.getId())) {
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
