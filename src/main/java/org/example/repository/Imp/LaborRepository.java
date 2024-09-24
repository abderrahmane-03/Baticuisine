package org.example.repository.Imp;

import org.example.DAO.Imp.LaborDAO;
import org.example.DAO.Inf.LaborDAOInterface;
import org.example.entities.Labor;
import org.example.repository.Inf.LaborRepositoryInf;

import java.util.List;

public class LaborRepository implements LaborRepositoryInf {
    private final LaborDAOInterface laborDAO;

    public LaborRepository(LaborDAOInterface laborDAO) {
        this.laborDAO = new LaborDAO();
    }

    @Override
    public void save(Labor labor) {
        laborDAO.insert(labor);
    }

    @Override
    public List<Labor> findByProjectId(int projectId) {
        return laborDAO.getLaborByProjectId(projectId);
    }
}
