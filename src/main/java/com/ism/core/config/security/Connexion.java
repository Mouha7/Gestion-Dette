package com.ism.core.config.security;

import java.util.Scanner;

import com.ism.core.factory.IFactory;
import com.ism.data.entities.User;
import com.ism.services.IUserSer;

public class Connexion {
    private Scanner scanner;
    private IUserSer userService;

    public Connexion(IFactory factory, Scanner scanner) {
        this.scanner = scanner;
        this.userService = factory.getFactoryService().getInstanceUserRepository();
    }
    
    public int connexion() {
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
        return user.getRole().ordinal();
    }


}
