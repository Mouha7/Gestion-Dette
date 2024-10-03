package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.User;
import com.ism.data.repository.IUserRepo;
import com.ism.services.IUserSer;

public class UserSer implements IUserSer {
    private IUserRepo userRepo;

    public UserSer(IUserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public boolean add(User value) {
        return userRepo.insert(value);
    }

    @Override
    public List<User> findAll() {
        return userRepo.selectAll();
    }

    @Override
    public User getBy(User user) {
        return userRepo.selectBy(user);
    }

    @Override
    public void setStatus(User user, boolean state) {
        userRepo.changeStatus(user, state);
    }

    @Override
    public List<User> getAllActifs(int type) {
        return userRepo.selectAllActifs(type);
    }

    @Override
    public User getByLogin(String login, String password) {
        return userRepo.selectByLogin(login, password);
    }

    @Override
    public int length() {
        return userRepo.length();
    }
}
