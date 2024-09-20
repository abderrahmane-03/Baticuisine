package org.example.services.Imp;

import org.example.DAO.Imp.MaterialDAO;
import org.example.entities.Material;
import org.example.entities.Material;

public class MaterialService {

    private final MaterialDAO materialDAO;

    public MaterialService() {
        this.materialDAO = new MaterialDAO();
    }

    public Material addMaterial(String name, double unitCost, double quantity, double transportCost, double qualityCoefficient, double vatRate) {
        Material material = new Material(name,  unitCost,  quantity,  transportCost,  qualityCoefficient, vatRate);
        materialDAO.insert(material);
        return material;
    }

}
