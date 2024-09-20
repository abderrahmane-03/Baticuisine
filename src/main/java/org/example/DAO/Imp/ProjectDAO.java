package org.example.DAO.Imp;

import org.example.DAO.Inf.ProjectDAOInterface;
import org.example.entities.Project;
import org.example.singleton.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO implements ProjectDAOInterface {

    @Override
    public void insert(Project project) {
        String query = "INSERT INTO project (projectName, clientId, totalCost, beneficiaryMargin, state) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setString(count++, project.getName());
            statement.setInt(count++, project.getClient().getClientId()); // Assuming Client has an ID.
            statement.setDouble(count++, project.getTotalCost());
            statement.setDouble(count++, project.getBeneficiaryMargin());
            statement.setString(count++, project.getState().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Project findByName(String name) {
        String query = "SELECT * FROM project WHERE projectName = ?";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Retrieve the project data
                    Project project = new Project(
                            resultSet.getString("projectName"),
                            resultSet.getDouble("beneficiaryMargin"),
                             null// You would typically load the client as well

                    );
                    project.setTotalCost(resultSet.getDouble("totalCost"));
                    return project;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Project> findAll() {
        String query = "SELECT * FROM project";
        List<Project> projects = new ArrayList<>();
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getString("projectName"),
                        resultSet.getDouble("beneficiaryMargin"),
                        null// You would typically load the client as well

                );
                project.setTotalCost(resultSet.getDouble("totalCost"));
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }
}
