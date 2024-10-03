package com.ism.views;

import java.util.List;

public interface IView<T> {
    T saisir();
    void afficher(List<T> list);
}
