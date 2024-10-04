package com.ism.core.factory;

import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDetteService;
import com.ism.services.IUserService;

public interface IFactoryService {
    IArticleService getInstanceArticleService();
    IClientService getInstanceClientService();
    IDetteService getInstanceDetteService();
    IUserService getInstanceUserService();
}
