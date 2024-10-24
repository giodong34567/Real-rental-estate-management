package com.javaweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO extends AbstractDTO{
    private Long id;
    private String name;
    private String street;
    private String ward;
    private String district;
    private Integer numberOfBasement;
    private Integer floorArea;
    private String level;
    private List<String> typeCode;
    private String overtimeFee;
    private String electricityFee;
    private String deposit;
    private String payment;
    private String rentTime;
    private String decorationTime;
    private String rentPriceDescription;
    private String carFee;
    private String motoFee;
    private String waterFee;
    private String structure;
    private String direction;
    private String note;
    private String rentArea;
    private String managerName;
    private String managerPhoneNumber;
    private Integer rentPrice;
    private String serviceFee;
    private Double brokerageFee;
    private String image;
    private String imageBase64;
    private String imageName;
}
