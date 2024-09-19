package org.example.services.Imp;
import entities.Client;
import java.util.ArrayList;

public class ClientService {
    private ArrayList<Client> clients;

    public ClientService() {
        clients = new ArrayList<>();
    }

    public Client addClient(String name, String address, String phone, boolean isProfessional) {
        Client client = new Client(name, address, phone, isProfessional);
        clients.add(client);
        return client;
    }

    public Client findClient(String name) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client;
            }
        }
        return null;
    }

    public void displayAllClients() {
        for (Client client : clients) {
            client.displayClientInfo();
        }
    }
}
