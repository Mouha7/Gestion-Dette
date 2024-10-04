package com.ism.core.factory.implement;

import com.ism.core.factory.IFactoryService;
import com.ism.core.factory.IFactoryView;
import com.ism.views.IArticleView;
import com.ism.views.IClientView;
import com.ism.views.IDetteView;
import com.ism.views.IUserView;
import com.ism.views.implement.ArticleView;
import com.ism.views.implement.ClientView;
import com.ism.views.implement.DetteView;
import com.ism.views.implement.UserView;

public class FactoryView implements IFactoryView {
    private IArticleView articleView;
    private IClientView clientView;
    private IDetteView detteView;
    private IUserView userView;
    private IFactoryService factoryService;

    public FactoryView(IFactoryService factoryService) {
        this.factoryService = factoryService;
    }

    @Override
    public IArticleView getInstanceArticleView() {
        return articleView == null ? new ArticleView(factoryService.getInstanceArticleService()) : articleView;
    }

    @Override
    public IClientView getInstanceClientView() {
        return clientView == null ? new ClientView(factoryService.getInstanceClientService()) : clientView;
    }

    @Override
    public IDetteView getInstanceDette() {
        return detteView == null ? new DetteView(factoryService.getInstanceDetteService()) : detteView;
    }

    @Override
    public IUserView getInstanceUserView() {
        return userView == null ? new UserView(factoryService.getInstanceUserService()) : userView;
    }
}
