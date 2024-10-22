package com.ism.data.repository.implement;

import java.util.List;
import java.util.stream.Collectors;

import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.Dette;
import com.ism.data.repository.IDetteRepository;

public class DetteRepository extends Repository<Dette> implements IDetteRepository {
    @Override
    public void changeStatus(Dette dette, boolean state) {
        Dette du = selectBy(dette);
        if (du != null) {
            du.setStatus(state);
        } else {
            throw new IllegalArgumentException("Dette not found");
        }
    }

    @Override
    public List<Dette> selectAllSoldes() {
        return selectAll().stream()
                .filter(dette -> dette.getMontantRestant() == 0)
                .collect(Collectors.toList());
    }

    @Override
    public List<Dette> selectAllNonSoldes() {
        return selectAll().stream()
                .filter(dette -> dette.getMontantRestant() != 0)
                .collect(Collectors.toList());
    }
}
