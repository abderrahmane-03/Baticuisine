package org.example.entities;


public class ProjectComponent {
    private int projectComponentId;
    private Project project;
    private Material material;
    private Labor labor;

    // Constructors, Getters, and Setters
    public ProjectComponent() {}

    public ProjectComponent(Project project, Material material, Labor labor) {
        this.project = project;
        this.material = material;
        this.labor = labor;
    }

    public int getProjectComponentId() {
        return projectComponentId;
    }

    public void setProjectComponentId(int projectComponentId) {
        this.projectComponentId = projectComponentId;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }
}

