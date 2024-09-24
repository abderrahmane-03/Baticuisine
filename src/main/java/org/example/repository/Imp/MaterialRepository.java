package org.example.repository.Imp;

import org.example.DAO.Imp.MaterialDAO;
import org.example.DAO.Inf.MaterialDAOInterface;
import org.example.entities.Material;
import org.example.repository.Inf.MaterialRepositoryInf;

import java.util.List;

public class MaterialRepository implements MaterialRepositoryInf {
    private final MaterialDAOInterface materialDAO;

    public MaterialRepository(MaterialDAOInterface materialDAO) {
        this.materialDAO = new MaterialDAO();
    }

    @Override
    public void save(Material material) {
        materialDAO.insert(material);
    }

    @Override
    public List<Material> findByProjectId(int projectId) {
        return materialDAO.getMaterialsByProjectId(projectId);
    }
}
