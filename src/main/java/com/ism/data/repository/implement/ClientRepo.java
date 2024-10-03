package com.ism.data.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.Client;
import com.ism.data.repository.IClientRepo;

public class ClientRepo extends Repository<Client> implements IClientRepo {
    @Override
    public List<Client> selectAllActifs() {
        return selectAll().stream()
                .filter(Client::isStatus)
                .collect(Collectors.toList());
    }

    @Override
    public void changeStatus(Client client, boolean state) {
        Client cl = selectBy(client);
        if (cl == null) {
            throw new IllegalArgumentException("Client not found");
        } else {
            cl.setStatus(state);
        }
    }

    @Override
    public boolean selectOne(String client) {
        // Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectOne'");
    }
}
