package org.example.entities;


public class Labor {
    private String type;
    private double hourlyRate;
    private double hoursWorked;
    private double productivityFactor;
    private double vatRate;

    public Labor(String type, double hourlyRate, double hoursWorked, double productivityFactor, double vatRate) {
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

    // Display method
    public void displayLaborInfo() {
        System.out.println(type + ": " + getTotalCost() + "€ (Hours: " + hoursWorked + ")");
    }
}

