package com.javaweb.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDTO extends AbstractDTO{
    private Long id;
    @NotBlank(message = "Tên tòa nhà không được để trống")
    private String name;

    @NotBlank(message = "Bạn chưa chọn quận")
    private String district;

    @NotBlank(message = "Tên phường không được để trống")
    private String ward;

    @NotBlank(message = "Tên đường không được để trống")
    private String street;

    private Integer numberOfBasement;
    private Integer floorArea;
    private String level;
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

    @NotBlank(message = "Diện tích thuê không được để trống")
    private String rentArea;
    private String managerName;
    private String managerPhoneNumber;

    @Min(value = 0, message = "Giá thuê phải là số dương")
    private Integer rentPrice;
    private String serviceFee;
    private Double brokerageFee;

    @NotNull(message = "Phải chọn ít nhất 1 loại tòa nhà")
    private List<String> typeCode;
    private String image;
    private String imageBase64;
    private String imageName;
}
