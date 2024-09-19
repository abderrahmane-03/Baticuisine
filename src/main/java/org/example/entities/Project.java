package org.example.entities;
import java.util.ArrayList;

public class Project {
    private String name;
    private Client client;
    private ArrayList<Material> materials;
    private ArrayList<Labor> labor;
    private double margin;
    private double totalCost;
    private ProjectState state;

    public Project(String name, Client client, double margin) {
        this.name = name;
        this.client = client;
        this.materials = new ArrayList<>();
        this.labor = new ArrayList<>();
        this.margin = margin;
        this.state = ProjectState.ONGOING;
    }

    public void addMaterial(Material material) {
        materials.add(material);
    }

    public void addLabor(Labor labor) {
        this.labor.add(labor);
    }

    public void calculateTotalCost() {
        double materialCost = materials.stream().mapToDouble(Material::getCostWithVAT).sum();
        double laborCost = this.labor.stream().mapToDouble(Labor::getCostWithVAT).sum();
        this.totalCost = materialCost + laborCost;
        this.totalCost += this.totalCost * (margin / 100); // Adding margin
    }

    public void displayProjectDetails() {
        System.out.println("Project: " + name);
        System.out.println("Client: " + client.getName());
        System.out.println("Total Cost: " + totalCost + "€");
    }
}
