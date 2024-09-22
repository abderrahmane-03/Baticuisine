package org.example.DAO.Imp;
import org.example.DAO.Inf.LaborDAOInterface;
import org.example.entities.Labor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LaborDAO implements LaborDAOInterface {
    @Override
    public void insert(Labor labor) {
        String query = "INSERT INTO labor (type, hourly_rate, hours_worked,productivity_factor,vatRate ) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = org.example.singleton.DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setString(count++, labor.getType());
            statement.setDouble(count++, Math.round(labor.getHourlyRate() * 100.0) / 100.0);
            statement.setDouble(count++, Math.round(labor.getWorkingHours() * 100.0) / 100.0);
            statement.setDouble(count++, Math.round(labor.getProductivityFactor() * 100.0) / 100.0);
            statement.setDouble(count++, Math.round(labor.getVatRate() * 100.0) / 100.0);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Labor> getLaborByProjectId(int projectId) {
        List<Labor> laborList = new ArrayList<>();
        String query = "SELECT * FROM labor WHERE project_id = ?";

        try (Connection connection = org.example.singleton.DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, projectId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Labor labor = new Labor(
                        rs.getString("type"),
                        rs.getDouble("hourly_rate"),
                        rs.getDouble("hours_worked"),
                        rs.getDouble("productivity_factor"),
                        rs.getDouble("tax_rate")
                );
                laborList.add(labor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return laborList;
    }

}
