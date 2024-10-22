package com.ism.data.repository;

import java.util.List;

import com.ism.core.repository.IRepository;
import com.ism.data.entities.Dette;

public interface IDetteRepository extends IRepository<Dette> {
    void changeStatus(Dette dette, boolean state);
    List<Dette> selectAllSoldes();
    List<Dette> selectAllNonSoldes();
}
