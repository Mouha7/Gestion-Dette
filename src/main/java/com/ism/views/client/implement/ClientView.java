package com.ism.views.client.implement;

import com.ism.data.entities.Client;
import com.ism.views.IClientView;
import com.ism.views.implement.ImpView;

public class ClientView extends ImpView<Client> implements IClientView {

    @Override
    public Client saisir() {
        Client client = new Client();
        System.out.print("Entrez le surnom : ");
        client.setSurname(scanner.nextLine());
        System.out.print("Entrez le tel : ");
        client.setTel(scanner.nextLine());
        System.out.print("Entrez l'adresse : ");
        client.setAddress(scanner.nextLine());
        client.setStatus(true);

        return client;
    }
}
