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
    public DemandeDette findBy(List<DemandeDette> demandeDettes,DemandeDette demandeDette) {
        for (DemandeDette dette : demandeDettes) {
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
    public void update(List<DemandeDette> demandeDettes, DemandeDette updateDemande) {
        for (int i = 0; i < demandeDettes.size(); i++) {
            if (demandeDettes.get(i).getIdDemandeDette() == updateDemande.getIdDemandeDette()) {
                demandeDettes.set(i, updateDemande);
                break; // Sortir de la boucle une fois que la mise à jour est effectuée
            }
        }
    }
}
