package com.ism.core.config.router;

import java.io.IOException;
import java.util.Scanner;

import com.ism.core.factory.IFactory;
import com.ism.core.helper.Tools;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDemandeArticleService;
import com.ism.services.IDemandeDetteService;
import com.ism.services.IDetailService;
import com.ism.services.IDetteService;
import com.ism.services.IPaiementService;
import com.ism.services.IUserService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import com.ism.controllers.IClientView;
import com.ism.controllers.IDemandeDetteView;
import com.ism.controllers.IDetteView;
import com.ism.controllers.IPaiementView;
import com.ism.controllers.IUserView;
import com.ism.controllers.admin.IApplicationAdmin;
import com.ism.controllers.admin.implement.ApplicationAdmin;
import com.ism.controllers.client.IApplicationClient;
import com.ism.controllers.client.implement.ApplicationClient;
import com.ism.controllers.store.IApplicationStorekeeper;
import com.ism.controllers.store.implement.ApplicationStorekeeper;

public class Router implements IRouter {
    private final IArticleService articleService;
    private final IClientService clientService;
    private final IClientView clientView;
    private final IDemandeDetteService demandeDetteService;
    private final IDemandeDetteView demandeDetteView;
    private final IDemandeArticleService demandeArticleService;
    private final IDetailService detailService;
    private final IDetteService detteService;
    private final IDetteView detteView;
    private final IPaiementService paiementService;
    private final IPaiementView paiementView;
    private final IUserService userService;
    private final IUserView userView;

    private final IApplicationAdmin appAdmin;
    private final IApplicationClient appClient;
    private final IApplicationStorekeeper appStorekeeper;
    private final Scanner scanner;

    public static User userConnect;
    public static String userParams;

    public Router(IFactory factory, Scanner scanner) {
        this.articleService = factory.getFactoryService().getInstanceArticleService();
        this.clientService = factory.getFactoryService().getInstanceClientService();
        this.clientView = factory.getFactoryView().getInstanceClientView();
        this.demandeDetteService = factory.getFactoryService().getInstanceDemandeDetteService();
        this.demandeDetteView = factory.getFactoryView().getInstanceDemandeDetteView();
        this.demandeArticleService = factory.getFactoryService().getInstanceDemandeArticleService();
        this.detailService = factory.getFactoryService().getInstanceDetailService();
        this.detteService = factory.getFactoryService().getInstanceDetteService();
        this.detteView = factory.getFactoryView().getInstanceDetteView();
        this.paiementService = factory.getFactoryService().getInstancePaiementService();
        this.paiementView = factory.getFactoryView().getInstancePaiementView();
        this.userService = factory.getFactoryService().getInstanceUserService();
        this.userView = factory.getFactoryView().getInstanceUserView();
        this.scanner = scanner;

        this.appAdmin = new ApplicationAdmin(this.scanner);
        this.appClient = new ApplicationClient(this.articleService, clientService, this.demandeDetteService,
                this.demandeDetteView, this.demandeArticleService, this.detteService, this.detteView, this.scanner);
        this.appStorekeeper = new ApplicationStorekeeper(this.articleService, this.clientService, this.clientView,
                this.demandeDetteService, this.demandeDetteView, this.detailService, this.detteService, this.detteView,
                this.paiementService, this.paiementView, userService, userView, this.scanner);
    }

    @FXML
    @Override
    public void navigate(ActionEvent e, User user) {
        userConnect = user;
        switch (user.getRole().name()) {
            case "ADMIN":
                try {
                    userParams = "Vous êtes connecté en tant que Admin (" + user.getLogin() + "🔴)";
                    Tools.load(e, "Gestion de Dette", "/com/ism/views/dashboard.admin.fxml");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                break;
            case "CLIENT":
                userParams = "Vous êtes connecté en tant que Client (" + user.getLogin() + "🔴)";
                appClient.run(user);
                break;
            case "BOUTIQUIER":
                userParams = "Vous êtes connecté en tant que Boutiquier (" + user.getLogin() + "🔴)";
                appStorekeeper.run(user);
                break;
            default:
                break;
        }
    }
}
