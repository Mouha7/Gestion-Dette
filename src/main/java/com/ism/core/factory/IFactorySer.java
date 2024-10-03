package com.ism.core.factory;

import com.ism.services.IClientSer;
import com.ism.services.IUserSer;

public interface IFactorySer {
    IClientSer getInstanceClientService();
    IUserSer getInstanceUserService();
}
