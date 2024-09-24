package org.example.repository.Imp;

import org.example.DAO.Imp.ClientDAO;
import org.example.DAO.Inf.ClientDAOInterface;
import org.example.entities.Client;
import org.example.repository.Inf.ClientRepositoryInf;

import java.util.List;

public class ClientRepository implements ClientRepositoryInf {
    private final ClientDAOInterface clientDAO;

    public ClientRepository(ClientDAOInterface clientDAO) {
        this.clientDAO = new ClientDAO();
    }

    @Override
    public void save(Client client) {
        clientDAO.insert(client);
    }

    @Override
    public Client findByName(String name) {
        return clientDAO.findByName(name);
    }

    @Override
    public List<Client> findAll() {
        return clientDAO.findAll();
    }
}
