package com.ism.views;

import java.util.List;

import com.ism.data.entities.DemandeDette;
import com.ism.data.entities.User;
import com.ism.services.IArticleService;
import com.ism.services.IDemandeDetteService;

public interface IDemandeDetteView extends IView<DemandeDette> {
    DemandeDette saisir(IArticleService articleService, User user);
    DemandeDette getObject(List<DemandeDette> list, IDemandeDetteService demandeDetteService);
}
