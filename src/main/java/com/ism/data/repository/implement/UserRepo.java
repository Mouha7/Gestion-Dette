package com.ism.data.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.User;
import com.ism.data.repository.IUserRepo;

public class UserRepo extends Repository<User> implements IUserRepo {

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
                .filter(user -> user.getLogin().compareTo(login) == 0 && user.getPassword().compareTo(password) == 0)
                .findFirst()
                .orElse(null);
    }

    @Override
    public int length() {
        return selectAll().size();
    }

}
