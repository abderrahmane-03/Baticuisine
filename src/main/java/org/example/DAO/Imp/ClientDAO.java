package org.example.DAO.Imp;

import org.example.DAO.Inf.ClientDAOInterface;
import org.example.entities.Client;
import org.example.singleton.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO implements ClientDAOInterface {


    @Override
    public void insert(Client client) {
        String query = "INSERT INTO client (name, address, phone_number, isProfessional) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            int count = 1;
            statement.setString(count++, client.getName());
            statement.setString(count++, client.getAddress());
            statement.setString(count++, client.getPhone());
            statement.setBoolean(count++, client.isProfessional());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Client findByName(String name) {

        String query = "SELECT * FROM client WHERE name = ?";
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Client client = new Client(
                            resultSet.getString("name"),
                            resultSet.getString("address"),
                            resultSet.getString("phone_number"),
                            resultSet.getBoolean("isprofessional")
                    );
                    client.setClientId(resultSet.getInt("client_id")); // Make sure to set client_id here
                    return client;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Client> findAll() {
        String query = "SELECT * FROM client";
        List<Client> clients = new ArrayList<>();
        try (Connection connection = DBConnection.getConnectionOrThrow();
             PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                clients.add(new Client(

                        resultSet.getString("name"),
                        resultSet.getString("address"),
                        resultSet.getString("phone_number"),
                        resultSet.getBoolean("isProfessional")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }
}
