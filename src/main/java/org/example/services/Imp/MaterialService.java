package org.example.services.Imp;

import org.example.entities.Material;
import org.example.entities.Project;
import org.example.repository.Inf.MaterialRepositoryInf;
import org.example.services.Inf.MaterialServiceInf;

import java.util.List;

public class MaterialService implements MaterialServiceInf {

    private final MaterialRepositoryInf materialRepository;

    public MaterialService(MaterialRepositoryInf materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public Material addMaterial(String name, double unitCost, double quantity, double transportCost, double qualityCoefficient, double vatRate, Project project) {
        Material material = new Material(name, unitCost, quantity, transportCost, qualityCoefficient, vatRate);
        material.setProject(project);
        materialRepository.save(material);
        return material;
    }

    @Override
    public List<Material> getMaterialsByProjectId(int projectId) {
        return materialRepository.findByProjectId(projectId);
    }
}
