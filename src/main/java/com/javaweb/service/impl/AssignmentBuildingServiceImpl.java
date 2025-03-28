package com.javaweb.service.impl;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.UserEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public void createAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO) {
        BuildingEntity buildingEntity = buildingRepository.findById(assignmentBuildingDTO.getBuildingId()).get();
        assignmentBuildingRepository.deleteAllByBuilding(buildingEntity);
        List<Long> staffIds = assignmentBuildingDTO.getStaffIds();
        for(Long staffId : staffIds) {
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuilding(buildingEntity);
            UserEntity userEntity = userRepository.findById(staffId).get();
            assignmentBuildingEntity.setUser(userEntity);
            assignmentBuildingRepository.save(assignmentBuildingEntity);
        }
    }


//    @Override
//    public List<AssignmentBuildingEntity> findByBuilding(BuildingEntity building) {
//        return Collections.emptyList();
//    }

//    @Override
//    List<AssignmentBuildingEntity> findByBuildingId(Long buildingId) {
//        return assignmentBuildingRepository.findByBuildingId(buildingId);
//    }
}
