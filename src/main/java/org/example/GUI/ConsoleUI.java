package org.example.GUI;
import org.example.entities.*;
import org.example.services.Imp.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.entities.Project.calculateTotalCost;

public class ConsoleUI {

    static ClientService clientService = new ClientService();
    static ProjectService projectService = new ProjectService();
    static MaterialService materialService = new MaterialService();
    static LaborService laborService = new LaborService();
    public static void run(Scanner sc) {
        System.out.println("=== Bienvenue dans l'application de gestion des projets de rénovation de cuisines ===");

        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");
            int option = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    createNewProject(sc, clientService, projectService, materialService, laborService);
                    break;
                case 2:
                    projectService.displayAllProjects();
                    break;
                case 3:
                    calculateProjectCost(sc, projectService, materialService, laborService);
                    break;
                case 4:
                    System.out.println("Au revoir !");
                    return;  // Exit the loop
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        }
    }

    private static void createNewProject(Scanner sc, ClientService clientService, ProjectService projectService,
                                         MaterialService materialService, LaborService laborService) {
        // Client search or creation logic
        Client client = searchOrAddClient(sc, clientService);

        // Creating new project in memory (not yet in the database)
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String projectName = sc.nextLine();

        System.out.print("Entrez la surface de la cuisine (en m²) : ");
        double surfaceArea = sc.nextDouble();
        sc.nextLine(); // Clear the buffer

        // Create project object but do NOT insert it into the database yet
        Project project = new Project(projectName, 0.0,surfaceArea,client );

        // Adding materials in memory
        List<Material> materials = addMaterials(sc, materialService);

        // Adding labor in memory
        List<Labor> laborList = addLabor(sc, laborService);

        // Perform cost calculations
        double totalCost = calculateTotalCost(materials, laborList);

        // Ask for confirmation
        System.out.println("--- Résultat du Calcul ---");
        // Display project details, materials, labor, etc.
        displayProjectSummary(project, materials, laborList, totalCost);

        System.out.print("Souhaitez-vous enregistrer ce projet et les détails associés ? (y/n) : ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            // Insert project into the database
            projectService.addProject(project);

            // Now insert materials and labor with the project ID
            for (Material material : materials) {
                materialService.addMaterial(material.getName(), material.getUnitCost(), material.getQuantite(),
                        material.getTransportCost(), material.getQualityCoefficient(),
                        material.getVatRate(), project);
            }

            for (Labor labor : laborList) {
                laborService.addLabor(labor.getType(), labor.getHourlyRate(), labor.getWorkingHours(),
                        labor.getProductivityFactor(), labor.getVatRate(), project); // Pass the project
            }


            System.out.println("Projet ajouté avec succès avec le client associé !");
        } else {
            System.out.println("Projet annulé.");
        }
    }



    private static Client searchOrAddClient(Scanner sc, ClientService clientService) {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");
        int clientOption = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (clientOption == 1) {
            System.out.println("--- Recherche de client existant ---");
            System.out.print("Entrez le nom du client : ");
            String name = sc.nextLine();
            Client client = clientService.findClient(name);
            if (client != null) {
                System.out.println("Client trouvé !");
                client.displayClientInfo();
                System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                String continueWithClient = sc.nextLine();
                if (continueWithClient.equalsIgnoreCase("y")) {
                    return client;
                }
            } else {
                System.out.println("Client non trouvé.");
            }
        }

        // Ajouter un nouveau client
        System.out.print("Entrez le nom du client : ");
        String name = sc.nextLine();
        System.out.print("Entrez l'adresse : ");
        String address = sc.nextLine();
        System.out.print("Entrez le numéro de téléphone : ");
        String phone = sc.nextLine();
        System.out.print("Est-ce un professionnel? (y/n) : ");
        boolean isProfessional = sc.nextLine().equalsIgnoreCase("y");
        return clientService.addClient(name, address, phone, isProfessional);
    }

    private static List<Material> addMaterials(Scanner sc, MaterialService materialService) {
        List<Material> materials = new ArrayList<>();
        System.out.println("--- Ajout des matériaux ---");
        while (true) {
            System.out.print("Entrez le nom du matériau : ");
            String materialName = sc.nextLine();
            System.out.print("Entrez la quantité de ce matériau (en m² ou litres) : ");
            double quantity = sc.nextDouble();
            System.out.print("Entrez le coût unitaire de ce matériau (€) : ");
            double unitCost = sc.nextDouble();
            System.out.print("Entrez le coût de transport de ce matériau (€) : ");
            double transportCost = sc.nextDouble();
            System.out.print("Entrez le coefficient de qualité du matériau (1.0 = standard, > 1.0 = haute qualité) : ");
            double qualityCoefficient = sc.nextDouble();
            sc.nextLine(); // Clear the buffer

            Material material = new Material(materialName, unitCost, quantity, transportCost, qualityCoefficient, 0.2);
            materials.add(material);

            System.out.println("Matériau ajouté avec succès !");
            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if (sc.nextLine().equalsIgnoreCase("n")) break;
        }
        return materials;
    }

    private static List<Labor> addLabor(Scanner sc, LaborService laborService) {
        List<Labor> laborList = new ArrayList<>();
        System.out.println("--- Ajout de la main-d'œuvre ---");
        while (true) {
            System.out.print("Entrez le type de main-d'œuvre (e.g., Ouvrier de base, Spécialiste) : ");
            String laborType = sc.nextLine();
            System.out.print("Entrez le taux horaire de cette main-d'œuvre (€/h) : ");
            double hourlyRate = sc.nextDouble();
            System.out.print("Entrez le nombre d'heures travaillées : ");
            double hoursWorked = sc.nextDouble();
            System.out.print("Entrez le facteur de productivité (1.0 = standard, > 1.0 = haute productivité) : ");
            double productivityFactor = sc.nextDouble();
            sc.nextLine(); // Clear the buffer

            Labor labor = new Labor(laborType, hourlyRate, hoursWorked, productivityFactor, 0.2);
            laborList.add(labor);

            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            if (sc.nextLine().equalsIgnoreCase("n")) break;
        }
        return laborList;
    }


    private static void calculateProjectCost(Scanner sc, ProjectService projectService, MaterialService materialService, LaborService laborService) {
        // Ask user for the project name or ID to retrieve the project
        System.out.println("--- Calcul du coût d'un projet ---");
        System.out.print("Entrez l'ID ou le nom du projet : ");
        String projectIdentifier = sc.nextLine();

        // Retrieve project by name or ID from the database
        Project project = projectService.findProject(projectIdentifier);

        if (project == null) {
            System.out.println("Projet introuvable !");
            return;
        }

        // Retrieve materials and labor linked to the project
        List<Material> materials = materialService.getMaterialsByProjectId(project.getProjectId());
        List<Labor> laborList = laborService.getLaborByProjectId(project.getProjectId());

        if (materials.isEmpty() && laborList.isEmpty()) {
            System.out.println("Aucun matériau ou main-d'œuvre associé à ce projet.");
            return;
        }

        // Calculate total cost
        double totalCost = calculateTotalCost(materials, laborList);

        // Display the results
        displayProjectSummary(project, materials, laborList, totalCost);
    }
    private static void displayProjectSummary(Project project, List<Material> materials, List<Labor> laborList, double totalCost) {
        System.out.println("--- Détail du Projet ---");
        System.out.println("Nom du projet : " + project.getName());
        System.out.println("Client : " + project.getClient().getName());

        // Display materials
        System.out.println("--- Matériaux ---");
        for (Material material : materials) {
            System.out.println(material.getName() + ": " + material.getTotalCost() + " €");
        }

        // Display labor
        System.out.println("--- Main-d'œuvre ---");
        for (Labor labor : laborList) {
            System.out.println(labor.getType() + ": " + labor.getTotalCost() + " €");
        }

        // Display total cost
        System.out.println("Coût total estimé : " + totalCost + " €");
    }



}
