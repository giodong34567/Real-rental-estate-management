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
import com.javaweb.service.BuildingService;
import com.javaweb.utils.UploadFileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.charset.StandardCharsets;
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
    private AssignmentBuildingRepository assignmentBuildingRepository;
    @Autowired
    private UploadFileUtils uploadFileUtils;

    @Override
    public Page<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest, Pageable pageable) {
        BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest);

        Page<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder, pageable);

        return buildingEntities.map(buildingConverter::convertToBuildingSearchResponse);
    }

    @Override
    public int countTotalItem(BuildingSearchRequest buildingSearchRequest) {
        return buildingRepository.countTotalItem(buildingSearchBuilderConverter.toBuildingSearchBuilder(buildingSearchRequest));
    }

    @Override
    @Transactional
    public void createOrUpdateBuilding(BuildingDTO buildingDTO){
        BuildingEntity buildingEntity = buildingConverter.convertToBuildingEntity(buildingDTO);
        saveThumbnail(buildingDTO, buildingEntity);
        buildingRepository.save(buildingEntity);
    }

    private void saveThumbnail(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        String path = "/building/" + buildingDTO.getImageName();
        if (buildingDTO.getImageBase64() != null) {
            if (buildingEntity.getImage() != null) {
                if (!path.equals(buildingEntity.getImage())) {
                    File file = new File("C://home/office" + buildingEntity.getImage());
                    file.delete();
                }
            }
            String base64Data = buildingDTO.getImageBase64();
            if(base64Data.contains(",")){
                base64Data = base64Data.split(",")[1];
            }
            byte[] bytes = Base64.decodeBase64(base64Data.getBytes(StandardCharsets.UTF_8));
            uploadFileUtils.writeOrUpdate(path, bytes);
            buildingEntity.setImage(path);
        }
    }

    @Override
    public BuildingDTO findById(Long id){
        BuildingEntity buildingEntity = buildingRepository.findById(id).orElse(null);
        return buildingConverter.convertToBuildingDTO(buildingEntity);
    }

    @Override
    @Transactional
    public void deleteByIdIn(List<Long> ids){
        assignmentBuildingRepository.deleteByBuildingIdIn(ids);
        buildingRepository.deleteByIdIn(ids);
    }

    @Override
    public Map<Long, String> loadStaffs() {
        return Collections.emptyMap();
    }
}
