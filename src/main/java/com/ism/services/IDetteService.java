package com.ism.services;

import java.util.List;

import com.ism.data.entities.Dette;

public interface IDetteService {
    boolean add(Dette value);
    List<Dette> findAll();
    int length();
    Dette findBy(Dette dette);
    void setStatus(Dette dette, boolean state);
    List<Dette> getAllSoldes();
    void update(List<Dette> dettes, Dette dette);
}
