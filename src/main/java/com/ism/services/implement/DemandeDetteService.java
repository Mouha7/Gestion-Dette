package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.DemandeDette;
import com.ism.data.repository.IDemandeDetteRepository;
import com.ism.services.IDemandeDetteService;

public class DemandeDetteService implements IDemandeDetteService {
    private IDemandeDetteRepository demandeDetteRepository;

    public DemandeDetteService(IDemandeDetteRepository demandeDetteRepository) {
        this.demandeDetteRepository = demandeDetteRepository;
    }

    @Override
    public boolean add(DemandeDette value) {
        return demandeDetteRepository.insert(value);
    }

    @Override
    public List<DemandeDette> findAll() {
        return demandeDetteRepository.selectAll();
    }

    @Override
    public DemandeDette findBy(DemandeDette demandeDette) {
        for (DemandeDette dette : demandeDetteRepository.selectAll()) {
            if (dette.getIdDemandeDette() == demandeDette.getIdDemandeDette()) {
                return dette;
            }
        }
        return null;
    }

    @Override
    public int length() {
        return demandeDetteRepository.size();
    }

    @Override
    public void update(DemandeDette demandeDette) {
        demandeDetteRepository.selectUpdate(demandeDette);
    }
}
