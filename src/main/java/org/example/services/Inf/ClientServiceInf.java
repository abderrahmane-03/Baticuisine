package org.example.services.Inf;

import org.example.entities.Client;
import java.util.List;

public interface ClientServiceInf {
    Client addClient(String name, String address, String phone, boolean isProfessional);
    Client findClient(String name);
    List<Client> getAllClients();
}
