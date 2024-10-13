package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Client;
import com.ism.services.IClientService;
import com.ism.views.IClientView;
import com.ism.views.IUserView;

public class ClientView extends ImpView<Client> implements IClientView {
    private IUserView userView;
    private IClientService clientService;

    public ClientView(IClientService clientService, IUserView userView) {
        this.clientService = clientService;
        this.userView = userView;
    }

    @Override
    public Client saisir() {
        Client client = new Client();
        System.out.print("Entrez le surnom: ");
        client.setSurname(scanner.nextLine()); // Faire la vérification du surname unique
        client.setTel(checkTel());
        System.out.print("Entrez l'adresse: ");
        client.setAddress(scanner.nextLine());
        client.setStatus(true);
        System.out.print("Voulez-vous enregistrer un compte utilisateur(O/N): ");
        String choix = scanner.nextLine();
        if (choix.equalsIgnoreCase("O")) {
            client.setUser(userView.accountCustomer());
        }
        return client;
    }

    private String checkTel() {
        String tel;
        do {
            System.out.print("Entrez le tel: ");
            tel = scanner.nextLine();
            if (!tel.matches("\\d{10}")) {
                System.out.println("Format incorrect, le tel doit contenir 10 chiffres.");
            }
        } while (!tel.matches("\\d{10}"));
        return tel;
    }

    @Override
    public Client getObject(List<Client> clients) {
        Client client;
        int choix;
        int count = clients.size();
        this.afficher(clients);
        do {
            client = new Client();
            System.out.print("Choisissez un client par son id: ");
            choix = scanner.nextInt();
            client.setIdClient(choix);
            client = clientService.findBy(client);
            if (client == null || choix > count) {
                System.out.println("Erreur, l'id est invalide.");
            }
        } while (client == null || choix > count);
        return client;
    }
}
