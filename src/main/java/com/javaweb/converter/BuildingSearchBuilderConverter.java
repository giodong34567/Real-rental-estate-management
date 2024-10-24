package com.javaweb.converter;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.model.request.BuildingSearchRequest;
import org.springframework.stereotype.Component;

@Component
public class BuildingSearchBuilderConverter {
    public BuildingSearchBuilder toBuildingSearchBuilder(BuildingSearchRequest buildingSearchRequest){
        BuildingSearchBuilder buildingSearchBuilder = new BuildingSearchBuilder.Builder()
            .setName(buildingSearchRequest.getName())
            .setFloorArea(buildingSearchRequest.getFloorArea())
            .setDistrict(buildingSearchRequest.getDistrict())
            .setWard(buildingSearchRequest.getWard())
            .setStreet(buildingSearchRequest.getStreet())
            .setNumberOfBasement(buildingSearchRequest.getNumberOfBasement())
            .setDirection(buildingSearchRequest.getDirection())
            .setLevel(buildingSearchRequest.getLevel())
            .setStartArea(buildingSearchRequest.getStartArea())
            .setEndArea(buildingSearchRequest.getEndArea())
            .setStartRentPrice(buildingSearchRequest.getStartRentPrice())
            .setEndRentPrice(buildingSearchRequest.getEndRentPrice())
            .setManagerName(buildingSearchRequest.getManagerName())
            .setManagerPhone(buildingSearchRequest.getManagerPhone())
            .setStaffId(buildingSearchRequest.getStaffId())
            .setTypeCode(buildingSearchRequest.getTypeCode())
            .build();
        return buildingSearchBuilder;
    }
}
