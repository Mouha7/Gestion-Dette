package com.ism.views;

import com.ism.data.entities.User;

public interface IUserView extends IView<User> {
    User accountCustomer();
    int typeRole();
}
