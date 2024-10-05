package com.ism.core.factory.implement;

import com.ism.core.factory.IFactoryRepository;
import com.ism.data.repository.IArticleRepository;
import com.ism.data.repository.IClientRepository;
import com.ism.data.repository.IDemandeArticleRepository;
import com.ism.data.repository.IDemandeDetteRepository;
import com.ism.data.repository.IDetteRepository;
import com.ism.data.repository.IUserRepository;
import com.ism.data.repository.implement.ArticleRepository;
import com.ism.data.repository.implement.ClientRepository;
import com.ism.data.repository.implement.DemandeArticleRepository;
import com.ism.data.repository.implement.DemandeDetteRepository;
import com.ism.data.repository.implement.DetteRepository;
import com.ism.data.repository.implement.UserRepository;

public class FactoryRepository implements IFactoryRepository {
    private IArticleRepository articleRepository;
    private IClientRepository clientRepository;
    private IDemandeArticleRepository demandeArticleRepository;
    private IDemandeDetteRepository demandeDetteRepository;
    private IDetteRepository detteRepository;
    private IUserRepository userRepository;

    @Override
    public IArticleRepository getInstanceArticleRepository() {
        return articleRepository == null ? new ArticleRepository() : articleRepository;
    }

    @Override
    public IDemandeArticleRepository getInstanceDemandeArticleRepository() {
        return demandeArticleRepository == null? new DemandeArticleRepository() : demandeArticleRepository;
    }
    
    @Override
    public IClientRepository getInstanceClientRepository() {
        return clientRepository == null ? new ClientRepository() : clientRepository;
    }

    @Override
    public IDemandeDetteRepository getInstanceDemandeDetteRepository() {
        return demandeDetteRepository == null ? new DemandeDetteRepository() : demandeDetteRepository;
    }

    @Override
    public IDetteRepository getInstanceDetteRepository() {
        return detteRepository == null ? new DetteRepository() : detteRepository;
    }

    @Override
    public IUserRepository getInstanceUserRepository() {
        if (userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }
}