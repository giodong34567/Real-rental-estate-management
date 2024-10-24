package com.javaweb.model.response;


import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuildingSearchResponse extends AbstractDTO {
    private String name;
    private String address;
    private Integer numberOfBasement;
    private String managerName;
    private String managerPhone;
    private Integer floorArea;
    private String rentArea;
    private String emptyArea;
    private Integer rentPrice;
    private String serviceFee;
    private Double brokerageFee;
}
