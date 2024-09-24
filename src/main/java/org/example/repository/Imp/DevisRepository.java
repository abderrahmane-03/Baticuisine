package org.example.repository.Imp;

import org.example.repository.Inf.DevisRepositoryInf;
import org.example.DAO.Inf.DevisDAOInterface;
import org.example.entities.Devis;

public class DevisRepository implements DevisRepositoryInf {

    private final DevisDAOInterface devisDAO;

    public DevisRepository(DevisDAOInterface devisDAO) {
        this.devisDAO = devisDAO;
    }

    @Override
    public void save(Devis devis) {
        devisDAO.insert(devis);
    }
}

