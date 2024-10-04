package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.User;
import com.ism.data.repository.IUserRepository;
import com.ism.services.IUserService;

public class UserService implements IUserService {
    private IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean add(User value) {
        return userRepository.insert(value);
    }

    @Override
    public List<User> findAll() {
        return userRepository.selectAll();
    }

    @Override
    public User findBy(User user) {
        return userRepository.selectBy(user);
    }

    @Override
    public void setStatus(User user, boolean state) {
        userRepository.changeStatus(user, state);
    }

    @Override
    public List<User> getAllActifs(int type) {
        return userRepository.selectAllActifs(type);
    }

    @Override
    public User getByLogin(String login, String password) {
        return userRepository.selectByLogin(login, password);
    }

    @Override
    public int length() {
        return userRepository.size();
    }
}
