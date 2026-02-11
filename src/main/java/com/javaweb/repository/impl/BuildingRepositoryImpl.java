package com.javaweb.repository.impl;

import com.javaweb.builder.SearchBuildingBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.NumberUtil;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl  implements BuildingRepository {
    public static void joinProcess(SearchBuildingBuilder searchBuildingBuilder, StringBuilder sql){
        if (searchBuildingBuilder.getDistrictCode() != null) sql.append(" join district on b.districtid = district.id ");
        if (searchBuildingBuilder.getStaffId() != null) sql.append(" join assignment_building on assignment_building.buildingid = b.id join user on user.id = assignment_building.staffid ");
        if (searchBuildingBuilder.getTypeCodes() != null && !searchBuildingBuilder.getTypeCodes().isEmpty() ) sql.append(" join building_renttype on building_renttype.buildingid = b.id join renttype on renttype.id = building_renttype.renttypeid ");
    }
    public static void nomarlQuery(SearchBuildingBuilder searchBuildingBuilder, StringBuilder where){
        Field[] fields = SearchBuildingBuilder.class.getDeclaredFields();
        try{
            for (Field field: fields){
                field.setAccessible(true);
                String fieldName = field.getName();
                if (fieldName.equals("rentAreaFrom") || fieldName.equals("rentAreaTo") || fieldName.equals("typeCodes") || fieldName.equals("rentPriceFrom") || fieldName.equals("rentPriceTo") || fieldName.equals("districtCode")) continue;
                Object obj = field.get(searchBuildingBuilder);
                if (obj == null) continue;
                String value = obj.toString();
                if (NumberUtil.isNumber(value)){
                    where.append(" and b." + fieldName + " = " + value + " ");
                }
                else where.append(" and b." + fieldName + " like '% " + value + "%' ");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void specialQuery(SearchBuildingBuilder searchBuildingBuilder,  StringBuilder where){
        if (searchBuildingBuilder.getDistrictCode() != null){
            where.append(" and district.code = '" + searchBuildingBuilder.getDistrictCode() + "' ");
        }
        if (searchBuildingBuilder.getRentAreaFrom() != null || searchBuildingBuilder.getRentAreaTo() != null){
            where.append(" and exists (select 1 from rent_area where b.id == rent_area.buildingid ");
            if (searchBuildingBuilder.getRentAreaFrom() != null){
                where.append(" and rent_area.value >= " + searchBuildingBuilder.getRentPriceFrom() + " ");
            }
            if (searchBuildingBuilder.getRentAreaTo() != null){
                where.append(" and rent_area.value <= " + searchBuildingBuilder.getRentAreaTo() + " ");
            }
            where.append(") ");
        }
        if (searchBuildingBuilder.getRentPriceFrom() != null){
            where.append(" and b.rentprice >= " + searchBuildingBuilder.getRentPriceFrom() + " ");
        }
        if (searchBuildingBuilder.getRentPriceTo() != null){
            where.append(" and b.rentprice <= " + searchBuildingBuilder.getRentPriceTo()+ " ");
        }

        if (searchBuildingBuilder.getTypeCodes() != null && !searchBuildingBuilder.getTypeCodes().isEmpty()){
            String tmp = searchBuildingBuilder.getTypeCodes().stream().map(i -> "'" + i + "'").collect(Collectors.joining(", "));
            where.append(" and renttype.code in (" + tmp + ") ");
        }
    }

    @Override
    public List<BuildingEntity> searchBuilding(SearchBuildingBuilder searchBuildingBuilder){
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.numberofbasement, b.districtid, b.street, b.ward, b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
        joinProcess(searchBuildingBuilder, sql);
        StringBuilder where =new StringBuilder( " WHERE 1 = 1 ");
        nomarlQuery(searchBuildingBuilder, where);
        specialQuery(searchBuildingBuilder, where);
        sql.append(where).append(" group by b.id");
        List<BuildingEntity> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batdongsan", "root", "582006")) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getLong("id"));
                buildingEntity.setDistrictId(rs.getLong("districtid"));
                buildingEntity.setNumberOfBasement(rs.getLong("numberofbasement"));
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setManagerName(rs.getString("managername"));
                buildingEntity.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                buildingEntity.setFloorArea(rs.getLong("floorarea"));
                buildingEntity.setRentPrice(rs.getLong("rentprice"));
                buildingEntity.setServiceFee(rs.getLong("servicefee"));
                buildingEntity.setBrokerageFee(rs.getLong("brokeragefee"));
                results.add(buildingEntity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
