package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    public BuildingSearchResponse convertToBuildingSearchResponse(BuildingEntity buildingEntity) {
        System.out.println("Converting BuildingEntity: " + buildingEntity.getId());
        List<RentAreaEntity> rentAreas = buildingEntity.getRentAreas();
        System.out.println("Rent Areas: " + rentAreas);

        BuildingSearchResponse buildingSearchResponse = modelMapper.map(buildingEntity, BuildingSearchResponse.class);
        String rentAreaString = rentAreas.stream().map(rentArea -> rentArea.getValue().toString()).collect(Collectors.joining(", "));
        buildingSearchResponse.setRentArea(rentAreaString);

        Map<String, String> districts = districtCode.type();
        String districtName = "";
        if(buildingEntity.getDistrict() != null && !buildingEntity.getDistrict().equals("")){
            districtName = districts.get(buildingEntity.getDistrict());
        }
        if(districtName != null && !districtName.equals("")){
            buildingSearchResponse.setAddress(buildingEntity.getStreet() + ", " + buildingEntity.getWard() + ", " + districtName);
        }
        System.out.println("Converted Response: " + buildingSearchResponse);
        return buildingSearchResponse;
    }

    public BuildingEntity convertToBuildingEntity(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);

        // Kiem tra xem da ton tai hay chua
        if (buildingDTO.getId() != null) {
            buildingEntity.setId(buildingDTO.getId());
            rentAreaRepository.deleteAllByBuildingIdIn(Arrays.asList(buildingDTO.getId()));
        }

        // Xu li RentType;
        if(buildingDTO.getTypeCode() != null && !buildingDTO.getTypeCode().isEmpty()){
            String typeCodeString = buildingDTO.getTypeCode().stream().map(String::trim).collect(Collectors.joining(", "));
            buildingEntity.setTypeCode(typeCodeString);
        }

        // Luu cai Repo truoc khi them rentArea
        buildingEntity = buildingRepository.save(buildingEntity);

        // Xu li rentArea
        if (buildingDTO.getRentArea() != null && !buildingDTO.getRentArea().isEmpty()) {
            List<String> listRentAreas = Arrays.asList(buildingDTO.getRentArea().split(","));
            List<Integer> listRentAreasInteger = listRentAreas.stream()
                    .map(String::trim)
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());

            for (Integer item : listRentAreasInteger) {
                RentAreaEntity rentAreaEntity = new RentAreaEntity();
                rentAreaEntity.setBuilding(buildingEntity);
                rentAreaEntity.setValue(item);
                rentAreaRepository.save(rentAreaEntity);
            }
        }

        return buildingEntity;
    }

    public BuildingDTO convertToBuildingDTO(BuildingEntity buildingEntity) {
        BuildingDTO buildingDTO = modelMapper.map(buildingEntity, BuildingDTO.class);
        List<RentAreaEntity> rentAreaEntities = buildingEntity.getRentAreas();
        String rentAreaString = rentAreaEntities.stream().map(rentArea -> rentArea.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(rentAreaString);
        List<String> typeCode = Arrays.stream(buildingEntity.getTypeCode().split(",")).map(String::trim).collect(Collectors.toList());
        buildingDTO.setTypeCode(typeCode);
        return buildingDTO;
    }
}
