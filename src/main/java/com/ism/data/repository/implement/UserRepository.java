package com.ism.data.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.core.helper.PasswordUtils;
import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.User;
import com.ism.data.repository.IUserRepository;

public class UserRepository extends Repository<User> implements IUserRepository {
    @Override
    public void changeStatus(User user, boolean state) {
        User us = selectBy(user);
        if (us != null) {
            us.setStatus(state);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public List<User> selectAllActifs(int type) {
        return selectAll().stream()
                .filter(user -> user.isStatus() && user.getRole().ordinal() == type)
                .collect(Collectors.toList());
    }

    @Override
    public User selectByLogin(String login, String password) {
        return selectAll().stream()
                .filter(user -> user.getLogin().compareTo(login) == 0 && PasswordUtils.checkPassword(password, user.getPassword()))
                .findFirst()
                .orElse(null);
    }
}
