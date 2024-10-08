package com.ism.data.repository.implement;

import com.ism.core.repository.implement.Repository;
import com.ism.data.entities.DemandeDette;
import com.ism.data.repository.IDemandeDetteRepository;

public class DemandeDetteRepository extends Repository<DemandeDette> implements IDemandeDetteRepository {
    @Override
    public void selectUpdate(DemandeDette demandeDette) {
        for (int i = 0; i < selectAll().size(); i++) {
            DemandeDette currentDemandeDette = selectAll().get(i);
            if (currentDemandeDette.equals(demandeDette)) {
                list.set(i, demandeDette);
                break;
            }
        }
    }
}
