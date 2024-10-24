package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuildingSearchRequest extends AbstractDTO {
    private String name;
    private Integer floorArea;
    private String district;
    private String ward;
    private String street;
    private Integer numberOfBasement;
    private String direction;
    private String level;
    private Integer startArea;
    private Integer endArea;
    private Integer startRentPrice;
    private Integer endRentPrice;
    private String managerName;
    private String managerPhone;
    private Long staffId;
    private List<String> typeCode;
}
