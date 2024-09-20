package org.example.services.Imp;

import org.example.DAO.Imp.ClientDAO;
import org.example.entities.Client;

public class ClientService {
    private final ClientDAO clientDAO;

    public ClientService() {
        this.clientDAO = new ClientDAO();
    }

    public Client addClient(String name, String address, String phone, boolean isProfessional) {
        Client client = new Client(name, address, phone, isProfessional);
        clientDAO.insert(client);
        return client;
    }

    public Client findClient(String name) {
        return clientDAO.findByName(name);
    }

}
