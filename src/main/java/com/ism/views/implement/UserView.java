package com.ism.views.implement;

import java.util.List;

import com.ism.data.entities.Client;
import com.ism.data.entities.User;
import com.ism.data.enums.Role;
import com.ism.services.IUserService;
import com.ism.views.IUserView;

public class UserView extends ImpView<User> implements IUserView {
    private IUserService userService;

    public UserView(IUserService userService) {
        this.userService = userService;
    }

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
            System.out.println("(1)- " + Role.values()[0].name());
            System.out.println("(2)- " + Role.values()[1].name());
            System.out.print("Choisissez un type d'utilisateur : ");
            choix = scanner.nextLine();
            if (!choix.equals("1") && !choix.equals("2")) {
                System.out.println("Erreur, choix invalide. Veuillez entrer 1 ou 2.");
            }
        } while (!choix.equals("1") && !choix.equals("2"));
        return Integer.parseInt(choix);
    }

    @Override
    public User getObject(List<User> users) {
        User user;
        int choix;
        int count = users.size();
        this.afficher(users);
        do {
            user = new User();
            System.out.print("Choisissez une user par son id: ");
            choix = scanner.nextInt();
            user.setIdUser(choix);
            user = userService.findBy(user);
            System.out.println(count);
            if (user == null || choix > count) {
                System.out.println("Erreur, l'id est invalide.");
            }
        } while (user == null || choix > count);
        return user;
    }
}
