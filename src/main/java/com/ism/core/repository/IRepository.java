package com.ism.core.repository;

import java.util.List;

public interface IRepository<T> {
    List<T> selectAll();
    boolean insert(T item);
    T selectBy(T object);
}
