package com.ism.core.helper;

public abstract class Helper {
    public void dump(Object object) {
        System.out.println(object);
    }

    public void die() {
        return;
    }

    public void dd(Object object) {
        dump(object);
        die();
    }
}
