package com.ism.views.implement;

import java.util.List;
import java.util.Scanner;

import com.ism.views.IView;

public abstract class ImpView<T> implements IView<T> {
    protected static Scanner scanner;

    public static void setScanner(Scanner scanner) {
        ImpView.scanner = scanner;
    }

    @Override
    public void afficher(List<T> list) {
        list.forEach(System.out::println);
    }
}
