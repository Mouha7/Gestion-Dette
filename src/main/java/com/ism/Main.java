package com.ism;

import java.util.Scanner;

import com.ism.core.config.router.IRouter;
import com.ism.core.config.router.Router;
import com.ism.core.config.security.Connexion;
import com.ism.core.config.security.IConnexion;
import com.ism.core.factory.IFactory;
import com.ism.core.factory.implement.Factory;
import com.ism.data.entities.User;
import com.ism.data.enums.Role;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IFactory factory = new Factory();
        IRouter router = new Router();
        // Création de user
        User user = new User("admin@admin.sn", "admin", "passer", true, Role.getRole("ADMIN"));
        factory.getFactoryRepository().getInstanceUserRepository().insert(user);

        IConnexion conn = new Connexion(factory.getFactoryService().getInstanceUserService(), scanner);
        router.navigate(conn.connexion(), factory, scanner);
    }
}