package org.example.repository.Inf;

import org.example.entities.Client;
import java.util.List;

public interface ClientRepositoryInf {
    void save(Client client);
    Client findByName(String name);
    List<Client> findAll();
}
