package org.example.services.Inf;

import org.example.entities.Material;
import org.example.entities.Project;

import java.util.List;

public interface MaterialServiceInf {
    Material addMaterial(String name, double unitCost, double quantity, double transportCost, double qualityCoefficient, double vatRate, Project project);
    List<Material> getMaterialsByProjectId(int projectId);
}
