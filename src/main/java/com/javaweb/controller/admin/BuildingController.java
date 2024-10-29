package com.javaweb.controller.admin;

import com.javaweb.enums.districtCode;
import com.javaweb.enums.buildingType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BuildingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute(value = "modelSearch") BuildingSearchRequest params) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");
        List<BuildingSearchResponse> responseList = buildingService.findAll(params);
        BuildingSearchResponse response = new BuildingSearchResponse();
        response.setListResult(responseList);
        modelAndView.addObject("response", response);
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        modelAndView.addObject("staffList", userService.listStaff());
        return modelAndView;
    }

    @GetMapping("/admin/building-edit")
    private ModelAndView buildingEdit(@ModelAttribute(value = "buildingEdit") BuildingDTO buildingDTO) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
//        modelAndView.addObject(buildingDTO);
        return modelAndView;
    }

    @GetMapping("/admin/building-edit-{id}")
    private ModelAndView buildingEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        // Lấy thông tin building từ service
        BuildingDTO buildingDTO = buildingService.findById(id);

        // Thêm các thuộc tính vào model
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());

        return modelAndView;
    }

}