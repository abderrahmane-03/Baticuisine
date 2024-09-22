package org.example.entities;


public class Labor {
    private String type;
    private double hourlyRate;
    private double hoursWorked;
    private double productivityFactor;
    private double vatRate;
    private Project project;

    public Labor(String type,double hourlyRate, double hoursWorked, double productivityFactor, double vatRate) {
        this.type = type;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.productivityFactor = productivityFactor;
        this.vatRate = vatRate;
    }

        public double getTotalCost() {
            return hourlyRate * hoursWorked * productivityFactor;
        }

        public double getCostWithVAT() {
            return getTotalCost() * (1 + vatRate / 100);
        }

        public void displayLaborInfo() {
            System.out.println(type + ": " + getTotalCost() + "€ (Hours: " + hoursWorked + ")");
        }
        public Project getProject() {
            return project;
        }

        public void setProject(Project project) {
            this.project = project;
        }

        public String getType() {
            return type ;
        }
        public Double getProductivityFactor() {
            return productivityFactor ;
        }
        public Double getVatRate() {
            return vatRate ;
        }

        public Double getHourlyRate() {
            return hourlyRate ;
        }

        public double getWorkingHours() {
            return hoursWorked ;
        }
}

