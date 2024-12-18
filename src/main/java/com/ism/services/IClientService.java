package com.ism.services;

import java.util.List;

import com.ism.data.entities.Client;

public interface IClientService {
    boolean add(Client value);
    List<Client> findAll();
    int length();
    Client findBy(Client client);
    Client findBy(List<Client> clients, Client client);
    void setStatus(Client client, boolean state);
    List<Client> getAllActifs();
    boolean findByTel(String client);
    List<Client> findAllCustomerAvailable();
    void update(List<Client> clients, Client updateClient);
}
