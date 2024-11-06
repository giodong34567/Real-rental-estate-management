package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

public interface BuildingService {
    Page<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable);
    int countTotalItem(BuildingSearchRequest buildingSearchRequest);
    void createOrUpdateBuilding(BuildingDTO buildingDTO);
    BuildingDTO findById(Long id);
    void deleteByIdIn(List<Long> ids);
    Map<Long, String> loadStaffs();
    void createAssignmentBuilding(AssignmentBuildingDTO assignmentBuildingDTO);
}
