package com.ism.core.factory.implement;

import com.ism.core.factory.IFactory;
import com.ism.core.factory.IFactoryRepo;
import com.ism.core.factory.IFactorySer;
import com.ism.core.factory.IFactoryView;

public class Factory implements IFactory {
    @Override
    public IFactoryRepo getFactoryRepository() {
        return new FactoryRepo();
    }

    @Override
    public IFactorySer getFactoryService() {
        return new FactorySer();
    }

    @Override
    public IFactoryView getFactoryView() {
        return new FactoryView();
    }    
}
