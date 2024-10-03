package com.ism.core.factory.implement;

import com.ism.core.factory.IFactoryView;
import com.ism.views.IClientView;
import com.ism.views.IUserView;
import com.ism.views.implement.ClientView;
import com.ism.views.implement.UserView;

public class FactoryView implements IFactoryView {
    private IClientView clientView;
    private IUserView userView;

    @Override
    public IClientView getInstanceClientView() {
        return clientView == null ? new ClientView() : clientView;
    }

    @Override
    public IUserView getInstanceUserView() {
        return userView == null ? new UserView() : userView;
    }
}
