package com.ism.views.client;

import com.ism.data.entities.Dette;
import com.ism.data.entities.User;
import com.ism.services.IDemandeDetteService;
import com.ism.services.IDetteService;
import com.ism.views.IApplication;
import com.ism.views.IDemandeDetteView;
import com.ism.views.IDetteView;

public interface IApplicationClient extends IApplication {
    int menu();
    void displayDette(IDetteService detteService, IDetteView detteView);
    void displayPaiement(Dette dette);
    void displayArticle(Dette dette);
    void subMenu(Dette dette);
    void saisirDette(IDemandeDetteService demandeDetteService, IDemandeDetteView demandeDetteView, User user);
}
