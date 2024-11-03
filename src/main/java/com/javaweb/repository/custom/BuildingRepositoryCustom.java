package com.javaweb.repository.custom;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BuildingRepositoryCustom {
    Page<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder, Pageable pageable);
    int countTotalItem(BuildingSearchBuilder buildingSearchBuilder);
}
