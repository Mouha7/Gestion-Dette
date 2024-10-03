package com.ism.core.factory.implement;

import com.ism.core.factory.IFactoryRepo;
import com.ism.data.repository.IClientRepo;
import com.ism.data.repository.IUserRepo;
import com.ism.data.repository.implement.ClientRepo;
import com.ism.data.repository.implement.UserRepo;

public class FactoryRepo implements IFactoryRepo {
    private IClientRepo clientRepo;
    private IUserRepo userRepo;

    @Override
    public IClientRepo getInstanceClientRepository() {
        return clientRepo == null ? new ClientRepo() : clientRepo;
    }

    @Override
    public IUserRepo getInstanceUserRepository() {
        return userRepo == null ? new UserRepo() : userRepo;
    }
    
}
