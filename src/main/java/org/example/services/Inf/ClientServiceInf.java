package org.example.services.Inf;

import org.example.entities.Client;

public interface ClientServiceInf {
    Client findClient(String name);
    Client addClient(String name, String address, String phone, boolean isProfessional);
}
