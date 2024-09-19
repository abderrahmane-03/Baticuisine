package org.example.GUI;
import org.example.entities.*;
import org.example.services.Imp.*;
import java.util.Scanner;

public class ConsoleUI {
    Scanner sc = new Scanner(System.in);
    ClientService clientService = new ClientService();
    ProjectService projectService = new ProjectService();
    MaterialService materialService = new MaterialService();
    LaborService laborService = new LaborService();

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
                calculateProjectCost(sc, projectService);
                break;
            case 4:
                System.out.println("Au revoir !");
                return;
            default:
                System.out.println("Option invalide !");
                break;
        }
    }


private static void createNewProject(Scanner sc, ClientService clientService, ProjectService projectService,
                                     MaterialService materialService, LaborService laborService) {
    // Client search or creation logic
    Client client = searchOrAddClient(sc, clientService);

    // Creating new project
    System.out.println("--- Création d'un Nouveau Projet ---");
    System.out.print("Entrez le nom du projet : ");
    String projectName = sc.nextLine();
    System.out.print("Entrez la surface de la cuisine (en m²) : ");
    double surface = sc.nextDouble();
    sc.nextLine(); // Consume newline

    Project project = new Project(projectName, client, 0.0);  // Margin initialized to 0.0, calculated later

    // Adding materials
    addMaterials(sc, project, materialService);

    // Adding labor
    addLabor(sc, project, laborService);

    projectService.addProject(project);
    System.out.println("Projet ajouté avec succès !");
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
        String clientName = sc.nextLine();
        Client client = clientService.findClient(clientName);
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

    // Add new client
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

private static void addMaterials(Scanner sc, Project project, MaterialService materialService) {
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
        sc.nextLine(); // Consume newline

        Material material = new Material(materialName, unitCost, quantity, transportCost, qualityCoefficient, 0.0);  // VAT applied later
        project.addMaterial(material);

        System.out.println("Matériau ajouté avec succès !");
        System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
        if (sc.nextLine().equalsIgnoreCase("n")) break;
    }
}

private static void addLabor(Scanner sc, Project project, LaborService laborService) {
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
        sc.nextLine(); // Consume newline

        Labor labor = new Labor(laborType, hourlyRate, hoursWorked, productivityFactor, 0.0);  // VAT applied later
        project.addLabor(labor);

        System.out.println("Main-d'œuvre ajoutée avec succès !");
        System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
        if (sc.nextLine().equalsIgnoreCase("n")) break;
    }
}

private static void calculateProjectCost(Scanner sc, ProjectService projectService) {
    System.out.println("--- Calcul du coût d'un projet ---");
    projectService.displayAllProjects();
    System.out.print("Entrez le nom du projet à calculer : ");
    String projectName = sc.nextLine();
    Project project = projectService.findProject(projectName);
    if (project == null) {
        System.out.println("Projet non trouvé !");
        return;
    }

    System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
    double vatRate = 0.0;
    if (sc.nextLine().equalsIgnoreCase("y")) {
        System.out.print("Entrez le pourcentage de TVA (%) : ");
        vatRate = sc.nextDouble();
        sc.nextLine(); // Consume newline
    }

    System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
    double margin = 0.0;
    if (sc.nextLine().equalsIgnoreCase("y")) {
        System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
        margin = sc.nextDouble();
        sc.nextLine(); // Consume newline
    }

    project.calculateTotalCost(vatRate, margin);
    project.displayProjectDetails();
}
}
