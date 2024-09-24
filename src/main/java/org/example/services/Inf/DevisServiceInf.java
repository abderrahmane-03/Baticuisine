package org.example.services.Inf;

import org.example.entities.Devis;
import org.example.entities.Project;

import java.sql.Date;

public interface DevisServiceInf {
    Devis createDevis(double montantEstime, Date dateEmission, Date dateValidite, boolean accepte, Project project);
}
