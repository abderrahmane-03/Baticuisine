package org.example.services.Imp;

import org.example.DAO.Imp.ProjectDAO;
import org.example.entities.Project;

public class ProjectService {
    private final ProjectDAO projectDAO;

    public ProjectService() {
        this.projectDAO = new ProjectDAO();
    }

    public void addProject(Project project) {
        projectDAO.insert(project);
    }

    public Project findProject(String projectName) {
        return projectDAO.findByName(projectName);
    }

    public void displayAllProjects() {
        projectDAO.findAll().forEach(Project::displayProjectDetails);
    }
}
