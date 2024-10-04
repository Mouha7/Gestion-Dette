package com.ism.core.config.router;

import java.util.Scanner;

import com.ism.core.factory.IFactory;

public interface IRouter {
    public void navigate(String role, IFactory factory, Scanner scanner);
}
