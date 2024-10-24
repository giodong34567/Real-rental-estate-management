package com.javaweb.service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest);
    void createOrUpdateBuilding(BuildingDTO buildingDTO);
    BuildingDTO findById(Long id);
    void deleteByIdIn(List<Long> ids);
    Map<Long, String> loadStaffs();
}
