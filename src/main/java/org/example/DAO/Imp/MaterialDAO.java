package org.example.DAO.Imp;

import org.example.DAO.Inf.MaterialDAOInterface;
import org.example.entities.Material;
import org.example.singleton.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO implements MaterialDAOInterface {

        @Override
        public void insert(Material material) {
            String query = "INSERT INTO material (project_id,name,quantity,unit_cost,transport_cost, quality_coefficient,vatrate) VALUES (?,?,?, ?, ?, ?, ?)";
            try (Connection connection = org.example.singleton.DBConnection.getConnectionOrThrow();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                int count = 1;
                statement.setInt(count++, material.getProject().getProjectId());
                statement.setString(count++, material.getName());

                double roundedQuantity = Math.min(Math.round(material.getQuantite() * 100.0) / 100.0, 9999.99);
                double roundedUnitCost = Math.min(Math.round(material.getUnitCost() * 100.0) / 100.0, 9999.99);
                double roundedTransportCost = Math.min(Math.round(material.getTransportCost() * 100.0) / 100.0, 9999.99);
                double roundedQualityCoefficient = Math.min(Math.round(material.getQualityCoefficient() * 100.0) / 100.0, 9999.99);
                double roundedVatRate = Math.min(Math.round(material.getVatRate() * 100.0) / 100.0, 99.99);

                statement.setDouble(count++, roundedQuantity);
                statement.setDouble(count++, roundedUnitCost);
                statement.setDouble(count++, roundedTransportCost);
                statement.setDouble(count++, roundedQualityCoefficient);
                statement.setDouble(count++, roundedVatRate);

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    @Override
    public List<Material> getMaterialsByProjectId(int projectId) {
        List<Material> materials = new ArrayList<>();
        String query = "SELECT * FROM material WHERE project_id = ?";

        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, projectId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Material material = new Material(
                        rs.getString("name"),
                        rs.getDouble("unit_cost"),
                        rs.getDouble("quantity"),
                        rs.getDouble("transport_cost"),
                        rs.getDouble("quality_coefficient"),
                        rs.getDouble("vatrate")
                );
                materials.add(material);
            }
            System.out.println("Found " + materials.size() + " materials for project ID " + projectId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materials;
    }

}
