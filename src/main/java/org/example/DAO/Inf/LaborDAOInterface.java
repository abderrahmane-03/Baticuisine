package org.example.DAO.Inf;

import org.example.entities.Labor;

import java.util.List;

public interface LaborDAOInterface {
    void insert(Labor labor);
    List<Labor> getLaborByProjectId(int projectId);
}
