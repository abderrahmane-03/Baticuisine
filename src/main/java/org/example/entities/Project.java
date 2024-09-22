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
    private double surfaceArea;
    private ProjectState etatProjet;  // Enum for project state
    private Client client;  // Association to Client
    private List<Material> materials;  // List of materials used in the project
    private List<Labor> labors;  // List of labor components
    private Devis devis;  // Associated Devis (estimate)

    // Constructors
    public Project(String nomProjet, double margeBeneficiaire,double surfaceArea, Client client) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.surfaceArea = surfaceArea;  // Initially, total cost is 0
        this.coutTotal = 0.0;
        this.etatProjet = ProjectState.ONGOING;  // Default state is "En cours"
        this.client = client;
        this.materials = new ArrayList<>();
        this.labors = new ArrayList<>();
    }

    // Getters and Setters
    public int getProjectId() {
        return projectId;
    }
    public double getSurface_area() {
        return surfaceArea;
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
    }

    // Method to add labor to the project
    public void addLabor(Labor labor) {
        this.labors.add(labor);
    }
    public void assignClient(Client client) {
        this.client = client;
    }
    // Method to recalculate total cost
    public static double calculateTotalCost(List<Material> materials, List<Labor> laborList) {
        double totalMaterialCost = materials.stream().mapToDouble(Material::getTotalCost).sum();
        double totalLaborCost = laborList.stream().mapToDouble(Labor::getTotalCost).sum();
        return totalMaterialCost + totalLaborCost;
    }



    public void displayProjectDetails() {
        return ;
    }

}
