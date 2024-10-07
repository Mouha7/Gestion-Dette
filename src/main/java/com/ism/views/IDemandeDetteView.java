package com.ism.views;

import com.ism.data.entities.DemandeDette;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;

public interface IDemandeDetteView extends IView<DemandeDette> {
    DemandeDette saisir(IArticleService articleService, User user);
}
