package com.ism.core.factory.implement;

import com.ism.core.factory.IFactoryRepository;
import com.ism.data.repository.IArticleRepository;
import com.ism.data.repository.IClientRepository;
import com.ism.data.repository.IDetteRepository;
import com.ism.data.repository.IUserRepository;
import com.ism.data.repository.implement.ArticleRepository;
import com.ism.data.repository.implement.ClientRepository;
import com.ism.data.repository.implement.DetteRepository;
import com.ism.data.repository.implement.UserRepository;

public class FactoryRepository implements IFactoryRepository {
    private IArticleRepository articleRepository;
    private IClientRepository clientRepository;
    private IDetteRepository detteRepository;
    private IUserRepository userRepository;

    @Override
    public IArticleRepository getInstanceArticleRepository() {
        return articleRepository == null ? new ArticleRepository() : articleRepository;
    }

    @Override
    public IClientRepository getInstanceClientRepository() {
        return clientRepository == null ? new ClientRepository() : clientRepository;
    }

    @Override
    public IDetteRepository getInstanceDetteRepository() {
        return detteRepository == null ? new DetteRepository() : detteRepository;
    }

    @Override
    public IUserRepository getInstanceUserRepository() {
        return userRepository == null ? new UserRepository() : userRepository;
    }
}
