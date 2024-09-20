package org.example.services.Imp;
import org.example.entities.Labor;
import org.example.DAO.Imp.LaborDAO;


public class LaborService {
    private final LaborDAO laborDAO;

    public LaborService() {
        this.laborDAO = new LaborDAO();
    }


    public Labor addLabor(String type, double hourly_rate, double hours_worked, double productivity_factor, double vatRate) {
        Labor labor = new Labor(type,hourly_rate, hours_worked, productivity_factor, vatRate);
        laborDAO.insert(labor);
        return labor;
    }
}
