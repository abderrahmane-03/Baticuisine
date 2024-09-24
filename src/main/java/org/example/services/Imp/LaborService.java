package org.example.services.Imp;

import org.example.entities.Labor;
import org.example.entities.Project;
import org.example.repository.Inf.LaborRepositoryInf;
import org.example.services.Inf.LaborServiceInf;

import java.util.List;

public class LaborService implements LaborServiceInf {
    private final LaborRepositoryInf laborRepository;

    public LaborService(LaborRepositoryInf laborRepository) {
        this.laborRepository = laborRepository;
    }

    @Override
    public Labor addLabor(String type, double hourly_rate, double hours_worked, double productivity_factor, double vatRate, Project project) {
        Labor labor = new Labor(type, hourly_rate, hours_worked, productivity_factor, vatRate);
        labor.setProject(project);
        laborRepository.save(labor);
        return labor;
    }

    @Override
    public List<Labor> getLaborByProjectId(int projectId) {
        return laborRepository.findByProjectId(projectId);
    }
}
