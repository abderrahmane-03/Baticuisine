package org.example.repository.Inf;

import org.example.entities.Labor;
import java.util.List;

public interface LaborRepositoryInf {
    void save(Labor labor);
    List<Labor> findByProjectId(int projectId);
}
