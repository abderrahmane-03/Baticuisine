package org.example.DAO.Inf;

import org.example.entities.Client;
import java.util.List;

public interface ClientDAOInterface {
    void insert(Client client);
    Client findByName(String name);
    List<Client> findAll();
}
