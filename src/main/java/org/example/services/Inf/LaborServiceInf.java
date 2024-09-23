package org.example.services.Inf;

import org.example.entities.Labor;
import org.example.entities.Project;

import java.util.List;

public interface LaborServiceInf {
    Labor addLabor(String type, double hourly_rate, double hours_worked, double productivity_factor, double vatRate, Project project) ;
    List<Labor> getLaborByProjectId(int projectId);
    }
