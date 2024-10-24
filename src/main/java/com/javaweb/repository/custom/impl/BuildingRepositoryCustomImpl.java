package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.entity.BuildingEntity;

@Repository
@Primary
public class BuildingRepositoryCustomImpl implements BuildingRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
        List<BuildingEntity> buildingEntities = new ArrayList<BuildingEntity>();

        // xu li cau lenh sql
        StringBuilder sql = new StringBuilder("select b.* from building as b");
        joinQuery(buildingSearchBuilder, sql);
        sql.append(" where 1 = 1");
        whereQueryNormal(buildingSearchBuilder, sql);
        whereQuerySpecial(buildingSearchBuilder, sql);
        sql.append(" group by b.id");
        String sqlString = sql.toString();
        Query query = entityManager.createNativeQuery(sqlString, BuildingEntity.class);
        buildingEntities = query.getResultList();
        return buildingEntities;
    }

    public void joinQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder join) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            join.append(" inner join assignmentbuilding as a on b.id = a.buildingid");
        }
        Integer startArea = buildingSearchBuilder.getStartArea();
        Integer endArea = buildingSearchBuilder.getEndArea();
        if (startArea != null || endArea != null) {
            join.append(" inner join rentarea as r on b.id = r.buildingid");
        }
    }

    public void whereQueryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("start")
                        && !fieldName.startsWith("end")) {
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null) {
                        if (item.getType().getName().equals("java.lang.Long") ||
                                item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" and b." + fieldName + " = " + value);
                        } else if(item.getType().getName().equals("java.lang.String")) {
                            where.append(" and b." + fieldName + " like '%" + value + "%'");
                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void whereQuerySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffId = buildingSearchBuilder.getStaffId();
        if (staffId != null) {
            where.append(" and a.staffid = " + staffId);
        }

        Integer startArea = buildingSearchBuilder.getStartArea();
        Integer endArea = buildingSearchBuilder.getEndArea();

        if (startArea != null || endArea != null) {
            if (startArea != null) {
                where.append(" and r.value >= " + startArea);
            }
            if (endArea != null) {
                where.append(" and r.value <= " + endArea);
            }
        }

        Integer startRentPrice = buildingSearchBuilder.getStartRentPrice();
        Integer endRentPrice = buildingSearchBuilder.getEndRentPrice();
        if (startRentPrice != null || endRentPrice != null) {
            if (startRentPrice != null) {
                where.append(" and b.rentprice >= " + startRentPrice);
            }
            if (endRentPrice != null) {
                where.append(" and b.rentprice <= " + endRentPrice);
            }
        }

//        List<String> typeCode = buildingSearchBuilder.getTypeCode();
//        if (typeCode != null && !typeCode.isEmpty()) {
//            String typeCodeString = typeCode.stream()
//                    .filter(Objects::nonNull)
//                    .map(str -> "" + str + "")
//                    .collect(Collectors.joining(","));
//
//            if (!typeCodeString.isEmpty()) {
//                where.append(" and b.type like concat('%', '" + typeCodeString + "', '%')");
//            }
//        }
        List<String> typeCode = buildingSearchBuilder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            String checked = typeCode.stream()
                    .map(code -> "b.type like '%" + code + "%'")
                    .collect(Collectors.joining(" or "));
            where.append(" and (").append(checked).append(")");
        }
    }
}
