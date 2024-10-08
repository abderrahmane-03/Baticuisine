package org.example.entities;


public class Material {
    private String name;
    private double unitCost;
    private double quantity;
    private double transportCost;
    private double qualityCoefficient;
    private double vatRate;

    private Project project;

    public Material(String name, double unitCost, double quantity, double transportCost, double qualityCoefficient, double vatRate) {
        this.name = name;
        this.unitCost = unitCost;
        this.quantity = quantity;
        this.transportCost = transportCost;
        this.qualityCoefficient = qualityCoefficient;
        this.vatRate = 20;
    }
    public String getName() {
        return name;
    }
    public double getUnitCost() {
        return unitCost;
    }
    public double getQualityCoefficient() {
        return qualityCoefficient;
    }
    public double getTransportCost() {
        return transportCost;
    }
    public Double getVatRate() {
        return vatRate ;
    }
    public double getQuantite() {
        return quantity;
    }
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public double getTotalCost() {
        return (unitCost * quantity * qualityCoefficient) + transportCost;
    }

    public double getCostWithVAT() {
        return getTotalCost() * (1 + vatRate / 100);
    }


    public void displayMaterialInfo() {
        System.out.println(name + ": " + getTotalCost() + "€ (Quantity: " + quantity + ")");
    }
}
