package com.ism.views;

import com.ism.data.entities.User;

public interface IApplication {
    int menu();
    void run(User user);
    void msgSuccess();
    void msgSuccess(String msg);
    boolean isEmpty(int size, String msg);
    void splice();
}
