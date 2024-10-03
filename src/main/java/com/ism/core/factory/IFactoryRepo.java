package com.ism.core.factory;

import com.ism.data.repository.IClientRepo;
import com.ism.data.repository.IUserRepo;

public interface IFactoryRepo {
    IClientRepo getInstanceClientRepository();
    IUserRepo getInstanceUserRepository();
}
