package com.javaweb.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum buildingType {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String name;

    buildingType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String, String> type(){
        Map<String, String> listType = new LinkedHashMap<>();
        for(buildingType item : buildingType.values()){
            listType.put(item.toString(), item.getName());
        }
        return listType;
    }
}
