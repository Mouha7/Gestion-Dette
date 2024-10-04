package com.ism.core.factory;

import com.ism.data.repository.IArticleRepository;
import com.ism.data.repository.IClientRepository;
import com.ism.data.repository.IDetteRepository;
import com.ism.data.repository.IUserRepository;

public interface IFactoryRepository {
    IArticleRepository getInstanceArticleRepository();
    IClientRepository getInstanceClientRepository();
    IDetteRepository getInstanceDetteRepository();
    IUserRepository getInstanceUserRepository();
}
