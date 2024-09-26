package org.example.GUI;
import org.example.DAO.Imp.*;
import org.example.entities.*;
import org.example.repository.Imp.*;
import org.example.services.Imp.*;
import org.example.services.Inf.DevisServiceInf;
import org.example.utils.Validation;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.example.entities.Project.calculateTotalCost;

public class ConsoleUI {

    static ClientService clientService = new ClientService(new ClientRepository(new ClientDAO()));
    static ProjectService projectService = new ProjectService(new ProjectRepository(new ProjectDAO()));
    static MaterialService materialService = new MaterialService(new MaterialRepository(new MaterialDAO()));
    static LaborService laborService = new LaborService(new LaborRepository(new LaborDAO()));
    static DevisServiceInf devisService = new DevisService(new DevisRepository(new DevisDAO()));

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
            sc.nextLine();

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
                    return;
                default:
                    System.out.println("Option invalide !");
                    break;
            }
        }
    }

    private static void createNewProject(Scanner sc, ClientService clientService, ProjectService projectService,
                                         MaterialService materialService, LaborService laborService) {

        Client client = searchOrAddClient(sc, clientService);
        if (client == null) {
            return;
        }
        System.out.println("--- Création d'un Nouveau Projet ---");
        System.out.print("Entrez le nom du projet : ");
        String projectName = sc.nextLine();

        double surfaceArea;
        do {
            System.out.print("Entrez la surface de la cuisine (en m², entre 5 et 1000) : ");
            surfaceArea = sc.nextDouble();
            sc.nextLine();
            if (!Validation.isValidRange((int) surfaceArea, 5, 1000)) {
                System.out.println("La surface doit être comprise entre 5 et 1000 m². Veuillez réessayer.");
            }
        } while (!Validation.isValidRange((int) surfaceArea, 5, 1000));

        Project project = new Project(projectName, 0.0, surfaceArea, client);

        List<Material> materials = addMaterials(sc, materialService);
        List<Labor> laborList = addLabor(sc, laborService);

        double totalCost = calculateTotalCost(materials, laborList);
        System.out.println(totalCost);

        System.out.print("Souhaitez-vous enregistrer ce projet et les détails associés ? (y/n) : ");
        if (sc.nextLine().equalsIgnoreCase("y")) {
            projectService.addProject(project);

            for (Material material : materials) {
                materialService.addMaterial(material.getName(), material.getUnitCost(), material.getQuantite(),
                        material.getTransportCost(), material.getQualityCoefficient(),
                        material.getVatRate(), project);
            }

            for (Labor labor : laborList) {
                laborService.addLabor(labor.getType(), labor.getHourlyRate(), labor.getWorkingHours(),
                        labor.getProductivityFactor(), labor.getVatRate(), project);
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
        sc.nextLine();

        if (clientOption == 1) {
            System.out.println("--- Recherche de client existant ---");
            String name;
            do {
                System.out.print("Entrez le nom du client : ");
                name = sc.nextLine();
            } while (!Validation.isValidString(name));

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
        if (clientOption == 2) {
        String name;
        do {
            System.out.print("Entrez le nom du client : ");
            name = sc.nextLine();
        } while (!Validation.isValidString(name));

        String address;
        do {
            System.out.print("Entrez l'adresse : ");
            address = sc.nextLine();
        } while (!Validation.isValidString(address));

        String phone;
        do {
            System.out.print("Entrez le numéro de téléphone : ");
            phone = sc.nextLine();
        } while (!Validation.isValidPhoneNumber(phone));

        System.out.print("Est-ce un professionnel? (y/n) : ");
        boolean isProfessional = sc.nextLine().equalsIgnoreCase("y");

            clientService.addClient(name, address, phone, isProfessional);
            System.out.println("Client ajouté avec succès !");
            return null;
    }
        return null;
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
            sc.nextLine();

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
            sc.nextLine();

            Labor labor = new Labor(laborType, hourlyRate, hoursWorked, productivityFactor, 0.2);
            laborList.add(labor);

            System.out.println("Main-d'œuvre ajoutée avec succès !");
            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            if (sc.nextLine().equalsIgnoreCase("n")) break;
        }
        return laborList;
    }

    private static void calculateProjectCost(Scanner sc, ProjectService projectService, MaterialService materialService, LaborService laborService) {
        System.out.println("--- Calcul du coût total ---");

        String projectIdentifier;
        do {
            System.out.print("Entrez l'ID ou le nom du projet : ");
            projectIdentifier = sc.nextLine();
        } while (!Validation.isValidString(projectIdentifier));

        Project project = projectService.findProject(projectIdentifier);

        if (project == null) {
            System.out.println("Projet introuvable !");
            return;
        }

        List<Material> materials = materialService.getMaterialsByProjectId(project.getProjectId());
        List<Labor> laborList = laborService.getLaborByProjectId(project.getProjectId());

        if (materials.isEmpty() && laborList.isEmpty()) {
            System.out.println("Aucun matériau ou main-d'œuvre associé à ce projet.");
            return;
        }

        System.out.print("Souhaitez-vous appliquer une TVA au projet ? (y/n) : ");
        boolean applyVAT = sc.nextLine().equalsIgnoreCase("y");
        double vatRate = 0.0;
        if (applyVAT) {
            System.out.print("Entrez le pourcentage de TVA (%) : ");
            vatRate = sc.nextDouble();
            sc.nextLine();
        }

        System.out.print("Souhaitez-vous appliquer une marge bénéficiaire au projet ? (y/n) : ");
        boolean applyMargin = sc.nextLine().equalsIgnoreCase("y");
        double marginRate = 0.0;
        if (applyMargin) {
            System.out.print("Entrez le pourcentage de marge bénéficiaire (%) : ");
            marginRate = sc.nextDouble();
            sc.nextLine();
        }

        double totalCost = calculateTotalCost(materials, laborList);
        double totalMaterialsCost = calculateMaterialsCost(materials);
        double totalLaborCost = calculateLaborCost(laborList);

        double totalMaterialsCostWithVAT = applyVAT ? totalMaterialsCost * (1 + vatRate / 100) : totalMaterialsCost;
        double totalLaborCostWithVAT = applyVAT ? totalLaborCost * (1 + vatRate / 100) : totalLaborCost;
        double totalCostWithVAT = totalMaterialsCostWithVAT + totalLaborCostWithVAT;

        double finalTotalCost = applyMargin ? totalCostWithVAT * (1 + marginRate / 100) : totalCostWithVAT;


        displayProjectSummary(project, materials, laborList, totalMaterialsCost, totalLaborCost, totalCost, totalCostWithVAT, finalTotalCost, vatRate, marginRate);

        System.out.println("--- Enregistrement du Devis ---");

        String issueDateStr, validityDateStr;
        do {
            System.out.print("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ");
            issueDateStr = sc.nextLine();
        } while (!Validation.isValidDate(issueDateStr, "dd/MM/yyyy"));

        do {
            System.out.print("Entrez la date de validité du devis (format : jj/mm/aaaa) : ");
            validityDateStr = sc.nextLine();
        } while (!Validation.isValidDate(validityDateStr, "dd/MM/yyyy"));

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date issueDate = new Date(dateFormat.parse(issueDateStr).getTime());
            Date validityDate = new Date(dateFormat.parse(validityDateStr).getTime());

            System.out.print("Souhaitez-vous enregistrer le devis ? (y/n) : ");
            if (sc.nextLine().equalsIgnoreCase("y")) {
                Devis devis = devisService.createDevis(finalTotalCost, issueDate, validityDate, false, project);
                System.out.println("Devis enregistré avec succès !");
                if (project != null) {
                    // Set the new attributes
                    project.setVatRate(vatRate);
                    project.setBeneficiaryMargin(marginRate);
                    project.setTotalCost(finalTotalCost);
                    projectService.update(project);

                    System.out.println("Le projet a été mis à jour avec succès.");
                } else {
                    System.out.println("Projet introuvable.");
                }
            }
        } catch (ParseException e) {
            System.out.println("Erreur de format de date. Veuillez utiliser le format jj/mm/aaaa.");
        }
    }

    private static void displayProjectSummary(Project project, List<Material> materials, List<Labor> laborList
            ,double totalMaterialsCost, double totalLaborCost, double totalCost,
                                              double totalCostWithVAT, double finalTotalCost, double vatRate, double marginRate) {
        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + project.getName());
        System.out.println("Surface : " + project.getSurface_area() + " m²");


        System.out.println("--- Matériaux ---");
        materials.forEach(material -> {
            System.out.printf("%s : %.2f € (quantité : %.2f, coût unitaire : %.2f €/unité, transport : %.2f €)\n",
                    material.getName(), material.getUnitCost() * material.getQuantite(),
                    material.getQuantite(), material.getUnitCost(), material.getTransportCost());
        });
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**\n", totalMaterialsCost);
        if (vatRate > 0) {
            System.out.printf("**Coût total des matériaux avec TVA (%.2f%%) : %.2f €**\n", vatRate, totalMaterialsCost * (1 + vatRate / 100));
        }


        System.out.println("--- Main-d'œuvre ---");
        laborList.forEach(labor -> {
            System.out.printf("%s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %.2f h, productivité : %.2f)\n",
                    labor.getType(), labor.getHourlyRate() * labor.getWorkingHours(),
                    labor.getHourlyRate(), labor.getWorkingHours(), labor.getProductivityFactor());
        });
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**\n", totalLaborCost);
        if (vatRate > 0) {
            System.out.printf("**Coût total de la main-d'œuvre avec TVA (%.2f%%) : %.2f €**\n", vatRate, totalLaborCost * (1 + vatRate / 100));
        }


        System.out.printf("**Coût total avant marge : %.2f €**\n", totalCost);
        if (marginRate > 0) {
            System.out.printf("Marge bénéficiaire (%.2f%%) : %.2f €\n", marginRate, totalCost * marginRate / 100);
        }
        System.out.printf("**Coût total final du projet : %.2f €**\n", finalTotalCost);
    }

    private static double calculateMaterialsCost(List<Material> materials) {
        return materials.stream().mapToDouble(material ->
                material.getUnitCost() * material.getQuantite() + material.getTransportCost()).sum();
    }

    private static double calculateLaborCost(List<Labor> laborList) {
        return laborList.stream().mapToDouble(labor ->
                labor.getHourlyRate() * labor.getWorkingHours() * labor.getProductivityFactor()).sum();
    }
}





