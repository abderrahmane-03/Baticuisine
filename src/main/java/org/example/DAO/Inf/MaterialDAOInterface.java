package org.example.DAO.Inf;

import org.example.entities.Material;
import java.util.List;

public interface MaterialDAOInterface {
    void insert(Material material);
    List<Material> getMaterialsByProjectId(int projectId);
}
