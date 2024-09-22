package org.example.services.Imp;

import org.example.DAO.Imp.MaterialDAO;
import org.example.entities.Material;
import org.example.entities.Material;
import org.example.entities.Project;

import java.util.List;

public class MaterialService {

    private final MaterialDAO materialDAO;

    public MaterialService() {
        this.materialDAO = new MaterialDAO();
    }

    public Material addMaterial(String name, double unitCost, double quantity, double transportCost, double qualityCoefficient, double vatRate, Project project) {
        Material material = new Material(name,  unitCost,  quantity,  transportCost,  qualityCoefficient, vatRate);
        material.setProject(project);
        materialDAO.insert(material);
        return material;
    }
    public List<Material> getMaterialsByProjectId(int projectId) {
        return materialDAO.getMaterialsByProjectId(projectId);
    }
}
