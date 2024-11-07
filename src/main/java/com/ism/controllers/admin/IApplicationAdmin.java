package com.ism.controllers.admin;

import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IClientService;
import com.ism.services.IDetteService;
import com.ism.services.IUserService;
import com.ism.controllers.IApplication;
import com.ism.controllers.IArticleView;
import com.ism.controllers.IClientView;
import com.ism.controllers.IDetteView;
import com.ism.controllers.IUserView;

public interface IApplicationAdmin extends IApplication {
    int role();
    int status();
    void msgStatus(boolean state);
    void soldes(IDetteService detteService, IDetteView detteView);
    void updateQte(IArticleService articleService, IArticleView articleView);
    void listingArticleAvailable(IArticleService articleService, IArticleView articleView);
    void createArticle(IArticleService articleService, IArticleView articleView);
    void listingUserActifs(IUserService userService, IUserView userView, User userConnect);
    void activeDesactiveAccount(IUserService userService, IUserView userView, User userConnect);
    void createAccountCustomer(IClientService clientService, IClientView clientView, IUserService userService, IUserView userView);
}
