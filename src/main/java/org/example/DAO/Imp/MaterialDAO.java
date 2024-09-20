package org.example.DAO.Imp;

import org.example.DAO.Inf.MaterialDAOInterface;
import org.example.entities.Material;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialDAO implements MaterialDAOInterface {

        @Override
        public void insert(Material material) {
            String query = "INSERT INTO material (project_id,name,quantity,unit_cost,transport_cost, quality_coefficient) VALUES (?,?, ?, ?, ?, ?)";
            try (Connection connection = org.example.singleton.DBConnection.getConnectionOrThrow();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                int count = 1;
                statement.setString(count++, material.getName());
                statement.setDouble(count++, material.getQualityCoefficient());
                statement.setDouble(count++, material.getUnitCost());
                statement.setDouble(count++, material.getTransportCost());
                statement.setDouble(count++, material.getQuantite());
                statement.setDouble(count++, material.getVatRate());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
