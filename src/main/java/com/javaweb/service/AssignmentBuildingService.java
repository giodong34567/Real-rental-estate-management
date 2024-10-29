package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignmentBuildingService {
        void createAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
