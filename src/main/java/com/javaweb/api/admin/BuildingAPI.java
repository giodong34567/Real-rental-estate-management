package com.javaweb.api.admin;

import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public void createOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        if (buildingDTO == null) {
            throw new IllegalArgumentException("BuildingDTO cannot be null");
        }
        buildingService.createOrUpdateBuilding(buildingDTO);
    }

    @DeleteMapping("/{ids}")
    public void deleteBuilding(@PathVariable String ids) {
        List<Long> idList = Arrays.stream(ids.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
        buildingService.deleteByIdIn(idList);
    }

    @GetMapping("/{id}")
    public Object loadStaff(@PathVariable Long id){
        List<StaffResponseDTO> listStaff = userService.listStaffResponse(id);
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(listStaff);
        responseDTO.setMessage("Success");
        return responseDTO;
    }
    @PutMapping
    public void createAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        assignmentBuildingService.createAssignmentBuilding(assignmentBuildingDTO);
    }
}

