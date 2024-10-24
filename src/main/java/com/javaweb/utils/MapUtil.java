//package com.javaweb.utils;
//
//import com.javaweb.builder.BuildingSearchBuilder;
//import com.javaweb.model.request.BuildingSearchRequest;
//
//import java.lang.reflect.Field;
//import java.util.Map;
//
//public class MapUtil {
//    public static <T> T getObject(Map<String, String> params, String key, Class<T> tClass) {
//        Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
//        for(Field item: fields) {
//            item.setAccessible(true);
//            String fieldName = item.getName();
//
//        }
//        Object value = params.getOrDefault(key, null);
//        if (value != null) {
//            if (tClass.getTypeName().equals("java.lang.String")) {
//                value = value != "" ? value.toString() : null;
//            }
//            else if (tClass.getTypeName().equals("java.lang.Integer")) {
//                value = value != "" ? Integer.valueOf(value.toString()) : null;
//            }
//            else if (tClass.getTypeName().equals("java.lang.Long")) {
//                value = value != "" ? Long.valueOf(value.toString()) : null;
//            }
//            return tClass.cast(value);
//        }
//        return null;
//    }
//    public static <T> T getObject1(BuildingSearchRequest buildingSearchRequest, String key, Class<T> tClass) {
//        Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
//        for(Field item: fields) {
//            item.setAccessible(true);
//            String fieldName = item.getName();
//
//        }
//        Object value = params.getOrDefault(key, null);
//        if (value != null) {
//            if (tClass.getTypeName().equals("java.lang.String")) {
//                value = value != "" ? value.toString() : null;
//            }
//            else if (tClass.getTypeName().equals("java.lang.Integer")) {
//                value = value != "" ? Integer.valueOf(value.toString()) : null;
//            }
//            else if (tClass.getTypeName().equals("java.lang.Long")) {
//                value = value != "" ? Long.valueOf(value.toString()) : null;
//            }
//            return tClass.cast(value);
//        }
//        return null;
//    }
//}
