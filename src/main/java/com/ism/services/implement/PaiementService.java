package com.ism.services.implement;

import java.util.List;

import com.ism.data.entities.Paiement;
import com.ism.data.repository.IPaiementRepository;
import com.ism.services.IPaiementService;

public class PaiementService implements IPaiementService {
    private IPaiementRepository paiementRepository;

    public PaiementService(IPaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public boolean add(Paiement value) {
        return paiementRepository.insert(value);
    }

    @Override
    public List<Paiement> findAll() {
        return paiementRepository.selectAll();
    }

    @Override
    public Paiement findBy(List<Paiement> paiements, Paiement paiement) {
        for (Paiement pay : paiements) {
            if (pay.getIdPaiement() == paiement.getIdPaiement()) {
                return pay;
            }
        }
        return null;
    }

    @Override
    public int length() {
        return paiementRepository.size();
    }
    
}
