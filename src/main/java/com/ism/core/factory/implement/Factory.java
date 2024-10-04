package com.ism.core.factory.implement;

import com.ism.core.factory.IFactory;
import com.ism.core.factory.IFactoryRepository;
import com.ism.core.factory.IFactoryService;
import com.ism.core.factory.IFactoryView;

public class Factory implements IFactory {
    @Override
    public IFactoryRepository getFactoryRepository() {
        return new FactoryRepository();
    }

    @Override
    public IFactoryService getFactoryService() {
        return new FactoryService(getFactoryRepository());
    }

    @Override
    public IFactoryView getFactoryView() {
        return new FactoryView(getFactoryService());
    }    
}
