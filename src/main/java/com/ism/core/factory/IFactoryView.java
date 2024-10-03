package com.ism.core.factory;

import com.ism.views.IClientView;
import com.ism.views.IUserView;

public interface IFactoryView {
    IClientView getInstanceClientView();
    IUserView getInstanceUserView();
}
