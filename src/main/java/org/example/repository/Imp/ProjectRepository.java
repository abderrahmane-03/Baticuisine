package org.example.repository.Imp;

import org.example.DAO.Imp.ProjectDAO;
import org.example.DAO.Inf.ProjectDAOInterface;
import org.example.entities.Project;
import org.example.repository.Inf.ProjectRepositoryInf;

import java.util.List;

public class ProjectRepository implements ProjectRepositoryInf {
    private final ProjectDAOInterface projectDAO;

    public ProjectRepository(ProjectDAOInterface projectDAO) {
        this.projectDAO = new ProjectDAO();
    }

    @Override
    public void save(Project project) {
        projectDAO.insert(project);
    }

    @Override
    public Project findByName(String name) {
        return projectDAO.findByName(name);
    }

    @Override
    public List<Project> findAll() {
        return projectDAO.findAll();
    }
    @Override
    public void update(Project project){
         projectDAO.update(project);

    }
}
