package org.example.entities;

import java.util.Date;

public class Devis {
    private int devisId;
    private double montantEstime;
    private Date dateEmission;
    private Date dateValidite;
    private boolean accepte;
    private Project project;  // Reference to Project

    // Constructors, Getters, and Setters
    public Devis() {}

    public Devis(double montantEstime, Date dateEmission, Date dateValidite, boolean accepte, Project project) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = accepte;
        this.project = project;
    }

    public int getDevisId() {
        return devisId;
    }

    public void setDevisId(int devisId) {
        this.devisId = devisId;
    }

    public double getMontantEstime() {
        return montantEstime;
    }

    public void setMontantEstime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public Date getDateEmission() {
        return dateEmission;
    }

    public void setDateEmission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public Date getDateValidite() {
        return dateValidite;
    }

    public void setDateValidite(Date dateValidite) {
        this.dateValidite = dateValidite;
    }

    public boolean isAccepte() {
        return accepte;
    }

    public void setAccepte(boolean accepte) {
        this.accepte = accepte;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
