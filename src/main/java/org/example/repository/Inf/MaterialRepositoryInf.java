package org.example.repository.Inf;

import org.example.entities.Material;
import java.util.List;

public interface MaterialRepositoryInf {
    void save(Material material);
    List<Material> findByProjectId(int projectId);
}
