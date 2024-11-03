package com.javaweb.controller.admin;

import com.javaweb.enums.districtCode;
import com.javaweb.enums.buildingType;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import com.javaweb.utils.DisplayTagUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class BuildingController {

    @Autowired
    private UserService userService;

    @Autowired
    private BuildingService buildingService;

    @GetMapping("/building-list")
    public ModelAndView buildingList(
            HttpServletRequest request,
            @ModelAttribute("modelSearch") BuildingSearchRequest params
    ) {
        ModelAndView modelAndView = new ModelAndView("admin/building/list");

        DisplayTagUtils.of(request, params);

        int page = Math.max(params.getPage() - 1, 0);
        int limit = (params.getLimit() != null) ? params.getLimit() : 20;
        Pageable pageable = PageRequest.of(page, limit);

        Page<BuildingSearchResponse> responsePage = buildingService.findAll(params, pageable);

        BuildingSearchResponse response = new BuildingSearchResponse();
        response.setListResult(responsePage.getContent());
        response.setTotalItems((int) responsePage.getTotalElements());

        // Thêm các thuộc tính vào model
        modelAndView.addObject("response", response);
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        modelAndView.addObject("staffList", userService.listStaff());

        return modelAndView;
    }

    @GetMapping("/building-edit")
    public ModelAndView buildingEdit(@ModelAttribute("buildingEdit") BuildingDTO buildingDTO) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        // Thêm các thuộc tính vào model
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());
        modelAndView.addObject("buildingEdit", buildingDTO);

        return modelAndView;
    }

    @GetMapping("/building-edit-{id}")
    public ModelAndView buildingEdit(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("admin/building/edit");

        BuildingDTO buildingDTO = buildingService.findById(id);

        // Thêm các thuộc tính vào model
        modelAndView.addObject("buildingEdit", buildingDTO);
        modelAndView.addObject("district", districtCode.type());
        modelAndView.addObject("rentType", buildingType.type());

        return modelAndView;
    }
}
