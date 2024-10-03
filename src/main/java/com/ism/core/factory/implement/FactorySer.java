package com.ism.core.factory.implement;

import com.ism.core.factory.IFactorySer;
import com.ism.services.IClientSer;
import com.ism.services.IUserSer;
import com.ism.services.implement.ClientSer;
import com.ism.services.implement.UserSer;

public class FactorySer implements IFactorySer {
    private IClientSer clientService;
    private IUserSer userService;
    private FactoryRepo factoryRepo = new FactoryRepo();

    @Override
    public IClientSer getInstanceClientService() {
        return clientService == null ? new ClientSer(factoryRepo.getInstanceClientRepository()) : clientService;
    }

    @Override
    public IUserSer getInstanceUserService() {
        return userService == null ? new UserSer(factoryRepo.getInstanceUserRepository()) : userService;
    }
    
}
