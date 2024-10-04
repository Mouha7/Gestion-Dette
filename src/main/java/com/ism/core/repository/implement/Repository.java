package com.ism.core.repository.implement;

import java.util.List;
import java.util.ArrayList;

import com.ism.core.repository.IRepository;

public class Repository<T> implements IRepository<T> {
    protected List<T> list = new ArrayList<>();

    @Override
    public List<T> selectAll() {
        return list;
    }

    @Override
    public boolean insert(T item) {
        return list.add(item);
    }

    @Override
    public T selectBy(T object) {
        return selectAll().stream()
                .filter(cl -> cl.equals(object))
                .findFirst()
                .orElse(null);
    }

    @Override
    public int size() {
        return list.size();
    }
}
