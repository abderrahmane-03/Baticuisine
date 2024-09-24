package org.example.services.Imp;

import org.example.entities.Project;
import org.example.repository.Inf.ProjectRepositoryInf;
import org.example.services.Inf.ProjectServiceInf;

public class ProjectService implements ProjectServiceInf {

    private final ProjectRepositoryInf projectRepository;

    public ProjectService(ProjectRepositoryInf projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public void addProject(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findProject(String projectName) {
        return projectRepository.findByName(projectName);
    }

    @Override
    public void displayAllProjects() {
        projectRepository.findAll().forEach(Project::displayProjectDetails);
    }
    @Override
    public void update(Project project){
        projectRepository.update(project);
    }
}
