package org.example.services.Imp;
import org.example.entities.Devis;
import org.example.entities.Project;
import org.example.repository.Inf.DevisRepositoryInf;
import org.example.services.Inf.DevisServiceInf;

import java.sql.Date;

public class DevisService implements DevisServiceInf {

    private final DevisRepositoryInf devisRepository;

    public DevisService(DevisRepositoryInf devisRepository) {
        this.devisRepository = devisRepository;
    }

    @Override
    public Devis createDevis(double montantEstime, Date dateEmission, Date dateValidite, boolean accepte, Project project) {
        Devis devis = new Devis(montantEstime, dateEmission, dateValidite, accepte, project);
        devisRepository.save(devis);  // Calls repository to persist the Devis
        return devis;
    }
}
