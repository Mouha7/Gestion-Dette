package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Client;
import com.ism.services.IClientService;
import com.ism.views.IClientView;

public class ClientView extends ImpView<Client> implements IClientView {
    private IClientService clientService;

    public ClientView(IClientService clientService) {
        this.clientService = clientService;
    }

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

    @Override
    public Client getObject(List<Client> clients) {
        Client client = new Client();
        int choix;
        int count = clients.size();
        this.afficher(clients);
        do {
            System.out.print("Choisissez un client par son id: ");
            choix = scanner.nextInt();
            client.setIdClient(choix);
            client = clientService.findBy(client);
            if (client == null || choix >= count) {
                System.out.println("Erreur, l'id est invalide.");
            }
        } while (client == null || choix >= count);
        return client;
    }
}
