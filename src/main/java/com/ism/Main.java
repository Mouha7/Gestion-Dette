package com.ism;

import java.util.Scanner;

import com.ism.core.config.security.Connexion;
import com.ism.core.factory.IFactory;
import com.ism.core.factory.implement.Factory;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        IFactory factory = new Factory();

        Connexion conn = new Connexion(factory, scanner);
        conn.connexion();
    }
}