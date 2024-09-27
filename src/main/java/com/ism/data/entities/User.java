package com.ism.data.entities;

import com.ism.data.enums.Role;

import lombok.Data;

@Data
public class User {
    private int idUser;
    private String email;
    private String login;
    private String password;
    private Role role;
}
