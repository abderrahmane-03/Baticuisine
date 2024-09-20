package org.example.DAO.Inf;

import org.example.entities.Project;
import java.util.List;

public interface ProjectDAOInterface {
    void insert(Project project);
    Project findByName(String name);
    List<Project> findAll();
}
