package org.example.entities;

import java.util.Date;

public class Devis {
    private int devisId;
    private double montantEstime;
    private Date dateEmission;
    private Date dateValidite;
    private boolean accepte;
    private Project project;


    public Devis(double montantEstime, Date dateEmission, Date dateValidite, boolean accepte, Project project) {
        this.montantEstime = montantEstime;
        this.dateEmission = dateEmission;
        this.dateValidite = dateValidite;
        this.accepte = false;
        this.project = project;
    }

    public int getDevisId() {
        return devisId;
    }

    public void setDevisId(int devisId) {
        this.devisId = devisId;
    }

    public double getMontantestime() {
        return montantEstime;
    }

    public void setMontantestime(double montantEstime) {
        this.montantEstime = montantEstime;
    }

    public java.sql.Date getDateemission() {
        return (java.sql.Date) dateEmission;
    }

    public void setDateemission(Date dateEmission) {
        this.dateEmission = dateEmission;
    }

    public java.sql.Date getDatevalidite() {
        return (java.sql.Date) dateValidite;
    }

    public void setDatevalidite(Date dateValidite) {
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
