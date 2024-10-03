package com.ism.core.factory;

public interface IFactory {
    IFactoryRepo getFactoryRepository();
    IFactorySer getFactoryService();
    IFactoryView getFactoryView();
}
