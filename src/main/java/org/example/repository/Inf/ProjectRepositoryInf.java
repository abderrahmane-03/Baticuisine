package org.example.repository.Inf;

import org.example.entities.Project;
import java.util.List;

public interface ProjectRepositoryInf {
    void save(Project project);
    Project findByName(String name);
    List<Project> findAll();
}
