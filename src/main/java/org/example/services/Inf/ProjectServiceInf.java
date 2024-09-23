package org.example.services.Inf;

import org.example.entities.Project;

public interface ProjectServiceInf {
    void addProject(Project project);
    Project findProject(String projectName);
    void displayAllProjects();
}
