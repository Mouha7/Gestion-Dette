package com.ism.views;

import com.ism.data.entities.DemandeDette;
import com.ism.data.entities.User;

public interface IDemandeDetteView extends IView<DemandeDette> {
    DemandeDette saisir(User user);
}
