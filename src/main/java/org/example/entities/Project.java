package org.example.entities;
import org.example.Enum.ProjectState;
import java.util.ArrayList;

import java.util.List;

public class Project {
    private int projectId;
    private String nomProjet;
    private double margeBeneficiaire;
    private double coutTotal;
    private double surfaceArea;
    private double tva;
    private ProjectState etatProjet;
    private Client client;
    private List<Material> materials;
    private List<Labor> labors;;


    public Project(String nomProjet, double margeBeneficiaire,double surfaceArea, Client client) {
        this.nomProjet = nomProjet;
        this.margeBeneficiaire = margeBeneficiaire;
        this.surfaceArea = surfaceArea;
        this.coutTotal = coutTotal;
        this.etatProjet = ProjectState.ONGOING;
        this.client = client;
        this.tva = tva;
        this.materials = new ArrayList<>();
        this.labors = new ArrayList<>();
    }


    public int getProjectId() {
        return projectId;
    }
    public double getSurface_area() {
        return surfaceArea;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }
    public double getVatRate() {
        return tva;
    }
    public void setVatRate(Double tva) {
        this.tva = tva;
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

    public void addMaterial(Material material) {
        this.materials.add(material);
    }

    public void addLabor(Labor labor) {
        this.labors.add(labor);
    }

    public void assignClient(Client client) {
        this.client = client;
    }

    public static double calculateTotalCost(List<Material> materials, List<Labor> laborList) {
        double totalMaterialCost = materials.stream().mapToDouble(Material::getTotalCost).sum();
        double totalLaborCost = laborList.stream().mapToDouble(Labor::getTotalCost).sum();
        return totalMaterialCost + totalLaborCost;
    }


    public void displayProjectDetails() {

            System.out.println("=== Informations sur le projet ===");
            System.out.println("Nom : " + nomProjet);
            System.out.println("surfaceArea : " + surfaceArea+"m²");
            System.out.println("etatProjet : " + etatProjet);
    }

}
