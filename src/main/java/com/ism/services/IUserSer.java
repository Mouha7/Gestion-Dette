package com.ism.services;

import java.util.List;

import com.ism.data.entities.User;

public interface IUserSer {
    boolean add(User value);
    List<User> findAll();
    User getBy(User user);
    void setStatus(User user, boolean state);
    List<User> getAllActifs(int type);
    User getByLogin(String login, String password);
    int length();
}
