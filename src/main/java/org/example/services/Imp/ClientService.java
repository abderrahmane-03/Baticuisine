package org.example.services.Imp;

import org.example.entities.Client;
import org.example.repository.Imp.ClientRepository;
import org.example.repository.Inf.ClientRepositoryInf;
import org.example.services.Inf.ClientServiceInf;

import java.util.List;

public class ClientService implements ClientServiceInf {
    private final ClientRepositoryInf clientRepository;

    public ClientService(ClientRepositoryInf clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public Client addClient(String name, String address, String phone, boolean isProfessional) {
        Client client = new Client(name, address, phone, isProfessional);
        clientRepository.save(client);
        return client;
    }

    @Override
    public Client findClient(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }
}
