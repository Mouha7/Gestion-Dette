package com.ism.services.implement;

import java.util.List;
import java.util.stream.Collectors;

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
        for (User us : userRepository.selectAll()) {
            if (us.getIdUser() == user.getIdUser()) {
                return us;
            }
        }
        return null;
    }

    @Override
    public User findBy(List<User> users, User user) {
        for (User us : users) {
            if (us.getIdUser() == user.getIdUser()) {
                return us;
            }
            if (user.getLogin() != null && us.getLogin().compareTo(user.getLogin()) == 0) {
                return us;
            }
            if (user.getEmail() != null && us.getEmail().compareTo(user.getEmail()) == 0) {
                return us;
            }
        }
        return null;
    }

    @Override
    public void setStatus(User user, boolean state) {
        userRepository.changeStatus(user, state);
    }

    @Override
    public List<User> getAllActifs(int type, User userConnect) {
        return userRepository.selectAllActifs(type)
                .stream()
                .filter(us -> us.getIdUser() != userConnect.getIdUser())
                .collect(Collectors.toList());
    }

    @Override
    public User getByLogin(String login, String password) {
        return userRepository.selectByLogin(login, password);
    }

    @Override
    public int length() {
        return userRepository.size();
    }

    @Override
    public void update(List<User> users, User updateUser) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getIdUser() == updateUser.getIdUser()) {
                users.set(i, updateUser);
                break; // Sortir de la boucle une fois que la mise à jour est effectuée
            }
        }
    }
}
