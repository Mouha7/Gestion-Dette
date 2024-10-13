package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.Client;
import com.ism.data.repository.IClientRepository;
import com.ism.services.IClientService;

public class ClientService implements IClientService {
    private IClientRepository clientRepository;

    public ClientService(IClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean add(Client client) {
        return clientRepository.insert(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.selectAll();
    }

    @Override
    public List<Client> findAllCustomerAvailable() {
        return clientRepository.selectAllCustomerAvailable();
    }

    @Override
    public int length() {
        return clientRepository.size();
    }

    @Override
    public Client findBy(Client client) {
        for (Client cl : findAll()) {
            if(cl.getUser().getIdUser() == client.getUser().getIdUser()) {
                return cl;
            }
        }
        return null;
    }

    @Override
    public Client findBy(List<Client> clients, Client client) {
        for (Client cl : clients) {
            if(cl.getUser().getIdUser() == client.getUser().getIdUser()) {
                return cl;
            }
            if (cl.getTel() != null && cl.getTel().compareTo(client.getTel()) == 0) {
                return cl;
            }
        }
        return null;
    }

    @Override
    public void setStatus(Client client, boolean state) {
        clientRepository.changeStatus(client, state);
    }

    @Override
    public List<Client> getAllActifs() {
        return clientRepository.selectAllActifs();
    }

    @Override
    public boolean findByTel(String client) {
        return clientRepository.selectOne(client);
    }
}
