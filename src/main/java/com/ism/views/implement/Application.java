package com.ism.views.implement;

import com.ism.views.IApplication;

public abstract class Application implements IApplication {
    protected static final String MSG_CHOICE = "Choisissez une option : ";
    protected static final String MSG_EXIT = "Merci d'avoir utiliser notre application, au revoir !";

    @Override
    public boolean isEmpty(int size, String msg) {
        if (size == 0) {
            System.out.println(msg);
            return true;
        }
        return false;
    }

    @Override
    public void msgSuccess() {
        msgSuccess("Ajouté avec succès !");
    }

    @Override
    public void msgSuccess(String msg) {
        System.out.println(msg);
    }

    @Override
    public void splice() {
        System.out.println("--------------------");
    }
}
