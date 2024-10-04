package com.ism.core.factory.implement;

import com.ism.core.factory.IFactoryRepository;
import com.ism.core.factory.IFactoryService;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDetteService;
import com.ism.services.IUserService;
import com.ism.services.implement.ArticleService;
import com.ism.services.implement.ClientService;
import com.ism.services.implement.DetteService;
import com.ism.services.implement.UserService;

public class FactoryService implements IFactoryService {
    private IArticleService articleService;
    private IClientService clientService;
    private IDetteService detteService;
    private IUserService userRepository;
    private IFactoryRepository factoryRepository;

    public FactoryService(IFactoryRepository factoryRepository) {
        this.factoryRepository = factoryRepository;
    }

    @Override
    public IArticleService getInstanceArticleService() {
        return articleService == null ? new ArticleService(factoryRepository.getInstanceArticleRepository()) : articleService;
    }

    @Override
    public IClientService getInstanceClientService() {
        return clientService == null ? new ClientService(factoryRepository.getInstanceClientRepository()) : clientService;
    }

    @Override
    public IDetteService getInstanceDetteService() {
        return detteService == null ? new DetteService(factoryRepository.getInstanceDetteRepository()) : detteService;
    }

    @Override
    public IUserService getInstanceUserService() {
        return userRepository == null ? new UserService(factoryRepository.getInstanceUserRepository()) : userRepository;
    }
}
