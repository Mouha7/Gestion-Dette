package com.ism.core.config.router;

import java.util.Scanner;

import com.ism.core.factory.IFactory;
import com.ism.views.admin.IApplicationAdmin;
import com.ism.views.admin.implement.ApplicationAdmin;
import com.ism.views.client.IApplicationClient;
import com.ism.views.client.implement.ApplicationClient;
import com.ism.views.store.IApplicationStorekeeper;
import com.ism.views.store.implement.ApplicationStorekeeper;

public class Router implements IRouter {
    
    @Override
    public void navigate(String role, IFactory factory, Scanner scanner) {
        switch (role) {
            case "ADMIN":
                IApplicationAdmin appAdmin = new ApplicationAdmin(factory, scanner);
                break;
            case "BOUTIQUIER":
                IApplicationClient appClient = new ApplicationClient();
            break;
            case "CLIENT":
                IApplicationStorekeeper appStorekeeper = new ApplicationStorekeeper();
            break;
            default:
                break;
        }
    }
}
