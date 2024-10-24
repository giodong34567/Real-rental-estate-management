package com.javaweb.service.impl;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private AssignmentBuildingRepository assignmentBuildingRepository;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
        List<BuildingSearchResponse> buildingSearchResponses = new ArrayList<>();
        for(BuildingEntity buildingEntity : buildingEntities) {
            BuildingSearchResponse buildingSearchResponse = buildingConverter.convertToBuildingSearchResponse(buildingEntity);
            buildingSearchResponses.add(buildingSearchResponse);
        }
        return buildingSearchResponses;
    }

    @Override
    @Transactional
    public void createOrUpdateBuilding(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = buildingConverter.convertToBuildingEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
    }

    @Override
    public BuildingDTO findById(Long id){
        BuildingEntity buildingEntity = buildingRepository.findById(id).get();
        BuildingDTO buildingDTO = buildingConverter.convertToBuildingDTO(buildingEntity);
        return buildingDTO;
    }


    @Override
    @Transactional
    public void deleteByIdIn(List<Long> ids){
        for(Long id : ids){
            BuildingEntity buildingEntity = buildingRepository.findById(id).get();
            rentAreaRepository.deleteAllByBuilding(buildingEntity);
        }
        assignmentBuildingRepository.deleteByBuildingIdIn(ids);
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public Map<Long, String> loadStaffs() {
        return Collections.emptyMap();
    }

}
