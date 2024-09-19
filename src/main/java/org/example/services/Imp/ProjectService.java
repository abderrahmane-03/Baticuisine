package org.example.services.Imp;
import entities.Project;
import java.util.ArrayList;

public class ProjectService {
    private ArrayList<Project> projects;

    public ProjectService() {
        projects = new ArrayList<>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void displayAllProjects() {
        for (Project project : projects) {
            project.displayProjectDetails();
        }
    }
}
