package com.ism.core.config.security;

import java.util.Scanner;

import com.ism.data.entities.User;
import com.ism.data.enums.Role;
import com.ism.services.IUserService;

public class Connexion implements IConnexion {
    private Scanner scanner;
    private IUserService userService;

    public Connexion(IUserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
        User user = new User("admin@admin.sn", "a", "a", true, Role.ADMIN);
        userService.add(user);
        User client = new User("client@admin.sn", "c", "c", true, Role.CLIENT);
        userService.add(client);
        User boutiquier = new User("boutiquier@admin.sn", "b", "b", true, Role.BOUTIQUIER);
        userService.add(boutiquier);
    }
    
    @Override
    public User connexion() {
        User user;
        do {
            System.out.println("Bienvenu sur l'application de gestion de dette");
            System.out.println("Veuillez-vous connecter");
            System.out.print("Login : ");
            String login = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();
            user = userService.getByLogin(login, password);
            if (user == null) {
                System.out.println("Login ou Mot de passe incorrect.");
            }
        } while (user == null);
        return user;
    }
}
