package org.example.entities;

public class Client {
    private int clientId;
    private String nom;
    private String adresse;
    private String telephone;
    private boolean isProfessional;


    public Client(String nom, String adresse, String telephone, boolean isProfessional) {

        this.nom = nom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.isProfessional = isProfessional;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getAddress() {
        return adresse;
    }

    public void setAddress(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone() {
        return telephone;
    }

    public void setPhone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isProfessional() {
        return isProfessional;
    }

    public void isProfessional(boolean estProfessionnel) {
        this.isProfessional = estProfessionnel;
    }
    public void displayClientInfo() {
        System.out.println("=== Informations sur le client ===");
        System.out.println("Nom : " + nom);
        System.out.println("Adresse : " + adresse);
        System.out.println("Téléphone : " + telephone);
        System.out.println("Type de client : " + (isProfessional ? "Professionnel" : "Particulier"));
    }

}
