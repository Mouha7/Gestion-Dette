package com.ism.views;

import com.ism.data.entities.Client;
import com.ism.data.entities.User;

public interface IUserView extends IView<User> {
    User accountCustomer(Client client);
    int typeRole();
}
