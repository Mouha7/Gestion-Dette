package com.ism.views.store.implement;

import com.ism.data.entities.Client;
import com.ism.views.IClientView;
import com.ism.views.implement.ImpView;

public class ClientView extends ImpView<Client> implements IClientView {

    @Override
    public Client saisir() {
        Client client = new Client();
        String choice;

        System.out.print("Entrez le surnom : ");
        client.setSurname(scanner.nextLine());
        System.out.print("Entrez le tel : ");
        client.setTel(scanner.nextLine());
        System.out.print("Entrez l'adresse : ");
        client.setAddress(scanner.nextLine());
        client.setStatus(true);
        do {
            System.out.print("Voulez-vous lui créer un compte : ");
            choice = scanner.nextLine().toLowerCase();
        } while(choice.equals("o") && choice.equals("n"));
        return client;
    }
}
