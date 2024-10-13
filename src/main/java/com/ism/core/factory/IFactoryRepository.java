package com.ism.core.factory;

import com.ism.data.repository.IArticleRepository;
import com.ism.data.repository.IClientRepository;
import com.ism.data.repository.IDemandeArticleRepository;
import com.ism.data.repository.IDemandeDetteRepository;
import com.ism.data.repository.IDetteRepository;
import com.ism.data.repository.IPaiementRepository;
import com.ism.data.repository.IUserRepository;

public interface IFactoryRepository {
    IArticleRepository getInstanceArticleRepository();
    IClientRepository getInstanceClientRepository();
    IDemandeArticleRepository getInstanceDemandeArticleRepository();
    IDemandeDetteRepository getInstanceDemandeDetteRepository();
    IDetteRepository getInstanceDetteRepository();
    IPaiementRepository getInstancePaiementRepository();
    IUserRepository getInstanceUserRepository();
}
