package org.example.services.Inf;

import org.example.entities.Project;
import java.util.List;

public interface ProjectServiceInf {
    void addProject(Project project);
    Project findProject(String projectName);
    void displayAllProjects();
}
