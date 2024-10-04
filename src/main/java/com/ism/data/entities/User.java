package com.ism.data.entities;

import com.ism.data.enums.Role;

import lombok.Data;

@Data
public class User {
    private int idUser;
    private String email;
    private String login;
    private String password;
    private boolean status;
    private Role role;
    private static int nbr;

    public User(String email, String login, String password, boolean status, Role role) {
        this.idUser = ++nbr;
        this.email = email;
        this.login = login;
        this.password = password;
        this.status = status;
        this.role = role;
    }

    public User() {
        this.idUser = ++nbr;
    }
}
