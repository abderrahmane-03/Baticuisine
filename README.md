BatiCuisine - Kitchen Construction Cost Estimation Application
Table of Contents
Project Overview
Key Features
Architecture
Technologies Used
Database Schema
Installation Guide
Usage
Future Improvements
Contributing
License
Project Overview
BatiCuisine is a Java-based application designed for professionals in the kitchen construction and renovation industry. The application helps contractors estimate the total cost of a kitchen construction project, including materials, labor, and other expenses. It also provides features to manage clients and projects, generate cost estimates (devis), and ensure efficient project management for professionals.

The application is aimed at offering:

Accurate project cost estimation
Client and project management
Flexibility to accommodate professionals working on kitchen construction
Key Features
Client Management: Manage information for professional and individual clients.
Project Creation and Management: Create, track, and manage kitchen construction projects.
Cost Estimation: Estimate project costs, including material and labor costs, profit margins, and taxes (TVA).
Devis (Quotes): Generate, track, and validate project quotes.
Material and Labor Management: Link materials and labor to specific projects for accurate cost breakdowns.
Architecture
The application is divided into several layers, each with distinct responsibilities:

DAO (Data Access Object): Responsible for interacting directly with the PostgreSQL database.

Examples: ClientDAO, ProjectDAO, MaterialDAO, LaborDAO, etc.
Repository: Acts as an abstraction layer between DAO and services, handling business logic and persistence.

Examples: ProjectRepository, ClientRepository, etc.
Service Layer: Encapsulates the business logic and interacts with the repositories.

Examples: ClientService, ProjectService, DevisService, etc.
Entities: Represent the core business objects, such as Client, Project, Material, Labor.

Singleton Pattern: Used to manage the database connection using a DBConnection class.

Technologies Used
Java: The main programming language used for business logic and application flow.
PostgreSQL: The relational database used for storing data.
JDBC: Java Database Connectivity, for interaction between the Java application and PostgreSQL.
Maven: For project build and dependency management.
Dotenv: For managing environment variables, such as database connection details.
Database Schema
The database consists of several tables used for managing clients, projects, materials, labor, and quotes (devis):

Client Table: Stores client information, including whether the client is a professional.

client_id, name, address, phone_number, is_professional
Project Table: Stores project details and links them to clients.

project_id, project_name, client_id, surface_area, tva, profit_margin, total_cost, etc.
Material Table: Stores material details used in projects.

material_id, project_id, name, quantity, unit_cost, transport_cost, etc.
Labor Table: Stores labor costs related to a project.

labor_id, project_id, type, hourly_rate, hours_worked, productivity_factor, etc.
Devis Table: Stores project quotes, including estimated costs and validity dates.

devis_id, montant_estime, date_emission, date_validite, accepte, project_id
Installation Guide
Prerequisites
Java 8+: Make sure you have Java 8 or higher installed.
PostgreSQL: Install PostgreSQL and ensure the database service is running.
Maven: Install Maven for dependency management.
Steps
Clone the Repository:

git clone https://github.com/your-username/batiCuisine.git
cd batiCuisine
Configure the Database:

Create a PostgreSQL database for the project.
Edit the .env file with your PostgreSQL credentials (username, password, and database name).
Initialize Database Schema:

Run the SQL schema located in scripts/database_init.sql to create necessary tables.

psql -U postgres -d BatiCuisine -f scripts/database_init.sql
Build the Project:

mvn clean install
Run the Application:

java -jar target/BatiCuisine.jar
Usage
Client Management:

Add new clients (individual or professional).
View client details and associated projects.
Project Management:

Create and manage projects.
Add materials and labor costs.
Estimate total project costs.
Devis (Quote) Management:

Generate project quotes.
View and validate quotes for client approval.
Future Improvements
User Authentication: Implement role-based authentication (e.g., Admin, Manager).
Report Generation: Add the ability to generate financial and project reports.
REST API: Implement a REST API for remote access to the application features.
User Interface: Develop a graphical user interface (GUI) for better user interaction.
