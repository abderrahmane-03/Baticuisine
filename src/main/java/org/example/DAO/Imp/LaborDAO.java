package org.example.DAO.Imp;
import org.example.DAO.Inf.LaborDAOInterface;
import org.example.entities.Labor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class LaborDAO implements LaborDAOInterface {
    @Override
    public void insert(Labor labor) {
        String query = "INSERT INTO labor (type, hourly_rate, hours_worked,productivity_factor,vatRate ) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = org.example.singleton.DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setString(count++, labor.getType());
            statement.setDouble(count++, labor.getHourlyRate());
            statement.setDouble(count++, labor.getWorkingHours());
            statement.setDouble(count++, labor.getProductivityFactor());
            statement.setDouble(count++, labor.getVatRate());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
