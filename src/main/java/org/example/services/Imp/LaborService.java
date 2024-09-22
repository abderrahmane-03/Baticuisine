package org.example.services.Imp;
import org.example.entities.Labor;
import org.example.DAO.Imp.LaborDAO;
import org.example.entities.Project;

import java.util.List;


public class LaborService {
    private final LaborDAO laborDAO;

    public LaborService() {
        this.laborDAO = new LaborDAO();
    }


    public Labor addLabor(String type, double hourly_rate, double hours_worked, double productivity_factor, double vatRate, Project project) {
        Labor labor = new Labor(type,hourly_rate, hours_worked, productivity_factor, vatRate);
        labor.setProject(project);
        laborDAO.insert(labor);
        return labor;
    }
    public List<Labor> getLaborByProjectId(int projectId) {
        return laborDAO.getLaborByProjectId(projectId);
    }
}
