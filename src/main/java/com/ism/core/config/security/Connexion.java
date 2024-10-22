package com.ism.core.config.security;

import java.util.Scanner;

import com.ism.core.helper.PasswordUtils;
import com.ism.data.entities.User;
import com.ism.data.enums.Role;
import com.ism.services.IUserService;

public class Connexion implements IConnexion {
    private Scanner scanner;
    private IUserService userService;

    public Connexion(IUserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
        User user = new User("admin@admin.sn", "a", PasswordUtils.hashPassword("a"), true, Role.ADMIN);
        userService.add(user);
        User client = new User("client@admin.sn", "c", PasswordUtils.hashPassword("c"), true, Role.CLIENT);
        userService.add(client);
        User boutiquier = new User("boutiquier@admin.sn", "b", PasswordUtils.hashPassword("b"), true, Role.BOUTIQUIER);
        userService.add(boutiquier);
    }
    
    @Override
    public User connexion() {
        User user = null;
        do {
            System.out.println("Bienvenue sur l'application de gestion de dette");
            System.out.println("Veuillez-vous connecter");
            // Demander le login
            System.out.print("Login : ");
            String login = scanner.nextLine();
            // Demander le mot de passe
            System.out.print("Password : ");
            String password = scanner.nextLine();
            // Récupération de l'utilisateur par login et mot de passe
            user = userService.getByLogin(login, password);
            // Vérification des conditions de connexion
            if (user == null) {
                System.out.println("Login ou Mot de passe incorrect.");
            } else if (!user.isStatus()) {
                System.out.println("Votre compte est désactivé.");
                user = null; // Pour forcer la boucle à redemander les identifiants
            }
        } while (user == null); // Tant que l'utilisateur est null (mauvais login ou compte désactivé)
        // Retourne l'utilisateur authentifié
        return user;
    }
    
}
