package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
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

    private BuildingSearchBuilder(Builder builder) {
        this.name = builder.name;
        this.floorArea = builder.floorArea;
        this.district = builder.district;
        this.ward = builder.ward;
        this.street = builder.street;
        this.numberOfBasement = builder.numberOfBasement;
        this.direction = builder.direction;
        this.level = builder.level;
        this.startArea = builder.startArea;
        this.endArea = builder.endArea;
        this.startRentPrice = builder.startRentPrice;
        this.endRentPrice = builder.endRentPrice;
        this.managerName = builder.managerName;
        this.managerPhone = builder.managerPhone;
        this.staffId = builder.staffId;
        this.typeCode = builder.typeCode;
    }

    public String getName() {
        return name;
    }

    public Integer getFloorArea() {
        return floorArea;
    }

    public String getDistrict() {
        return district;
    }

    public String getWard() {
        return ward;
    }

    public String getStreet() {
        return street;
    }

    public Integer getNumberOfBasement() {
        return numberOfBasement;
    }

    public String getDirection() {
        return direction;
    }

    public String getLevel() {
        return level;
    }

    public Integer getStartArea() {
        return startArea;
    }

    public Integer getEndArea() {
        return endArea;
    }

    public Integer getStartRentPrice() {
        return startRentPrice;
    }

    public Integer getEndRentPrice() {
        return endRentPrice;
    }

    public String getManagerName() {
        return managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public Long getStaffId() {
        return staffId;
    }

    public List<String> getTypeCode() {
        return typeCode;
    }

    public static class Builder {
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

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setFloorArea(Integer floorArea) {
            this.floorArea = floorArea;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setWard(String ward) {
            this.ward = ward;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setNumberOfBasement(Integer numberOfBasement) {
            this.numberOfBasement = numberOfBasement;
            return this;
        }

        public Builder setDirection(String direction) {
            this.direction = direction;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setStartArea(Integer startArea) {
            this.startArea = startArea;
            return this;
        }

        public Builder setEndArea(Integer endArea) {
            this.endArea = endArea;
            return this;
        }

        public Builder setStartRentPrice(Integer startRentPrice) {
            this.startRentPrice = startRentPrice;
            return this;
        }

        public Builder setEndRentPrice(Integer endRentPrice) {
            this.endRentPrice = endRentPrice;
            return this;
        }

        public Builder setManagerName(String managerName) {
            this.managerName = managerName;
            return this;
        }

        public Builder setManagerPhone(String managerPhone) {
            this.managerPhone = managerPhone;
            return this;
        }

        public Builder setStaffId(Long staffId) {
            this.staffId = staffId;
            return this;
        }

        public Builder setTypeCode(List<String> typeCode) {
            this.typeCode = typeCode;
            return this;
        }

        public BuildingSearchBuilder build() {
            return new BuildingSearchBuilder(this);
        }
    }
}
