package com.ism.core.factory;

import com.ism.views.IArticleView;
import com.ism.views.IClientView;
import com.ism.views.IDetteView;
import com.ism.views.IUserView;

public interface IFactoryView {
    IArticleView getInstanceArticleView();
    IClientView getInstanceClientView();
    IUserView getInstanceUserView();
    IDetteView getInstanceDette();
}
