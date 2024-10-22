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
    public Dette findBy(List<Dette> dettes, Dette dette) {
        for (Dette d : dettes) {
            if (d.getIdDette() == dette.getIdDette()) {
                return d;
            }
        }
        return null;
    }

    @Override
    public void setStatus(Dette dette, boolean state) {
        detteRepository.changeStatus(dette, state);
    }

    @Override
    public List<Dette> getAllSoldes() {
        return detteRepository.selectAllSoldes();
    }

    @Override
    public List<Dette> getAllNonSoldes() {
        return detteRepository.selectAllNonSoldes();
    }

    @Override
    public void update(List<Dette> dettes, Dette updatedDette) {
        for (int i = 0; i < dettes.size(); i++) {
            if (dettes.get(i).getIdDette() == updatedDette.getIdDette()) {
                dettes.set(i, updatedDette);
                break; // Sortir de la boucle une fois que la mise à jour est effectuée
            }
        }
    }
}
