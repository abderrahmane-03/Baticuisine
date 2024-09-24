package org.example.DAO.Imp;

import org.example.DAO.Inf.DevisDAOInterface;
import org.example.entities.Devis;
import org.example.singleton.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DevisDAO implements DevisDAOInterface {
    @Override
    public void insert(Devis devis) {
        String query = "INSERT INTO devis (montantestime, dateemission, datevalidite, accepte,project_id) VALUES (?,?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setDouble(count++, devis.getMontantestime());
            statement.setDate(count++, devis.getDateemission());
            statement.setDate(count++, devis.getDatevalidite());
            statement.setBoolean(count++, devis.isAccepte());
            statement.setInt(count++, devis.getProject().getProjectId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
