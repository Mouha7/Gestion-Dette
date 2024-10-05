package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.Dette;
import com.ism.data.repository.IDetteRepository;
import com.ism.services.IDetteService;

public class DetteService implements IDetteService {
    private IDetteRepository detteRepository;

    public DetteService(IDetteRepository detteRepository) {
        this.detteRepository = detteRepository;
    }

    @Override
    public boolean add(Dette value) {
        return detteRepository.insert(value);
    }

    @Override
    public List<Dette> findAll() {
        return detteRepository.selectAll();
    }

    @Override
    public int length() {
        return detteRepository.size();
    }

    @Override
    public Dette findBy(Dette dette) {
        return detteRepository.selectBy(dette);
    }

    @Override
    public void setStatus(Dette dette, boolean state) {
        detteRepository.changeStatus(dette, state);
    }

    @Override
    public List<Dette> getAllSoldes() {
        return detteRepository.selectAllSoldes();
    }
}
