package com.ism.services.implement;

import com.ism.data.entities.Client;
import com.ism.data.repository.IClientRepo;
import com.ism.services.IClientSer;

public class ClientSer implements IClientSer {
    private IClientRepo clientRepo;

    public ClientSer(IClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }

    @Override
    public boolean add(Client value) {
        return clientRepo.insert(value);
    }
}
