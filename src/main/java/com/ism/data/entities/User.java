package com.ism.data.entities;

import com.ism.data.enums.Role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class User {
    private int idUser;
    // Unique identifier
    private String email;
    // Unique identifier
    private String login;
    private String password;
    private boolean status;
    private Role role;
    private static int nbr;
    private Client client;

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

    @Override
    public String toString() {
        return "User [idUser=" + idUser + ", email=" + email + ", login=" + login + ", password=" + password
                + ", status=" + status + ", role=" + role + ", client=" + (client == null ? "N/A" : client.getSurname()) + "]";
    }
}
