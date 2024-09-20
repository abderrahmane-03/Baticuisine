package org.example.entities;
import org.example.Enum.ProjectState;
import java.util.ArrayList;

import java.util.List;
import java.util.ArrayList;

public class Project {
    private int projectId;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private ProjectState etatProjet;  // Enum for project state
    private Client client;  // Association to Client
    private List<Material> materials;  // List of materials used in the project
    private List<Labor> labors;  // List of labor components
    private Devis devis;  // Associated Devis (estimate)

    // Constructors
    public Project(String nomProjet, double margeBeneficiaire, Client client) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.coutTotal = 0.0;  // Initially, total cost is 0
        this.etatProjet = ProjectState.ONGOING;  // Default state is "En cours"
        this.client = client;
        this.materials = new ArrayList<>();
        this.labors = new ArrayList<>();
    }

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return nomProjet;
    }

    public void setName(String nomProjet) {
        this.nomProjet = nomProjet;
    }

    public double getBeneficiaryMargin() {
        return margeBeneficiaire;
    }

    public void setBeneficiaryMargin(double margeBeneficiaire) {
        this.margeBeneficiaire = margeBeneficiaire;
    }

    public double getTotalCost() {
        return coutTotal;
    }

    public void setTotalCost(double coutTotal) {
        this.coutTotal = coutTotal;
    }

    public ProjectState getState() {
        return etatProjet;
    }

    public void setState(ProjectState etatProjet) {
        this.etatProjet = etatProjet;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public List<Labor> getLabors() {
        return labors;
    }

    public Devis getDevis() {
        return devis;
    }

    public void setDevis(Devis devis) {
        this.devis = devis;
    }

    // Method to add material to the project
    public void addMaterial(Material material) {
        this.materials.add(material);
        recalculateTotalCost();
    }

    // Method to add labor to the project
    public void addLabor(Labor labor) {
        this.labors.add(labor);
        recalculateTotalCost();
    }
    public void assignClient(Client client) {
        this.client = client;
    }
    // Method to recalculate total cost
    private void recalculateTotalCost() {
        double totalMaterialsCost = materials.stream()
                .mapToDouble(material -> material.getCoutUnitaire().doubleValue() * material.getQuantite())
                .sum();
        double totalLaborsCost = labors.stream()
                .mapToDouble(labor -> labor.getTauxHoraire().doubleValue() * labor.getHeuresTravail())
                .sum();
        this.coutTotal = (totalMaterialsCost + totalLaborsCost) * (1 + this.margeBeneficiaire);
    }
}
