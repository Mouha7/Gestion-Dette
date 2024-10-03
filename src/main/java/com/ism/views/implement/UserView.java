package com.ism.views.implement;

import com.ism.data.entities.Client;
import com.ism.data.entities.User;
import com.ism.data.enums.Role;
import com.ism.views.IUserView;

public class UserView extends ImpView<User> implements IUserView {
    @Override
    public User accountCustomer(Client client) {
        User user = new User();
        System.out.print("Entrez l'email : ");
        user.setEmail(scanner.nextLine());
        System.out.print("Entrez le login : ");
        user.setLogin(scanner.nextLine());
        System.out.print("Entrez le mot de passe : ");
        user.setPassword(scanner.nextLine());
        user.setStatus(true);
        user.setRole(Role.CLIENT);
        return user;
    }

    @Override
    public User saisir() {
        User user = new User();
        System.out.print("Entrez l'email : ");
        user.setEmail(scanner.nextLine());
        System.out.print("Entrez le login : ");
        user.setLogin(scanner.nextLine());
        System.out.print("Entrez le mot de passe : ");
        user.setPassword(scanner.nextLine());
        user.setRole(Role.values()[typeRole() - 1]);
        user.setStatus(true);
        return user;
    }

    @Override
    public int typeRole() {
        String choix;
        do {
            for (Role role : Role.values()) {
                System.out.println("(" + (role.ordinal() + 1) + ")- " + role.name());
            }
            System.out.print("Choisissez un type d'utilisateur : ");
            choix = scanner.nextLine();
            if (!choix.equals("1") && !choix.equals("2")) {
                System.out.println("Erreur, choix invalide. Veuillez entrer 1 ou 2.");
            }
        } while (!choix.equals("1") && !choix.equals("2"));
        return Integer.parseInt(choix);
    }
}
