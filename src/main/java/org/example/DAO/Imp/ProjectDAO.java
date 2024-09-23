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
        String query = "INSERT INTO project (project_name, client_id, surface_area, tva, profit_margin, total_cost, state) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING project_id";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setString(count++, project.getName());
            statement.setInt(count++, project.getClient().getClientId());
            statement.setDouble(count++, project.getSurface_area());
            statement.setDouble(count++, 20); // TVA value
            statement.setDouble(count++, project.getBeneficiaryMargin());
            statement.setDouble(count++, project.getTotalCost());
            statement.setString(count++, project.getState().name());

            // Use executeQuery to get the returned project_id
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    // Retrieve and set the generated project ID
                    project.setProjectId(rs.getInt("project_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    public Project findByName(String name) {
        String query = "SELECT * FROM project WHERE project_name = ?";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Project project = new Project(
                            resultSet.getString("project_name"),
                            resultSet.getDouble("profit_margin"),

                            resultSet.getDouble("surface_area"),
                            null

                    );
                    project.setProjectId(resultSet.getInt("project_id"));
                    project.setTotalCost(resultSet.getDouble("total_cost"));
                    System.out.println("Retrieved project ID: " + project.getProjectId());

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
                        resultSet.getDouble("surfaceArea"),

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
