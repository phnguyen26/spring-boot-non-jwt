package com.javaweb.repository.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.utils.NumberUtil;
import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

@Repository
public class BuildingRepositoryImpl  implements BuildingRepository {
    public static void joinProcess(Map<String, String> params, List<String> typeCodes,  StringBuilder sql){
        if (params.containsKey("districtCode")) sql.append(" join district on b.districtid = district.id ");
        if (params.containsKey("rentAreaFrom") || params.containsKey("rentAreaTo")) sql.append(" join rent_area on rent_area.buildingid = b.id ");
        if (params.containsKey("staffId")) sql.append(" join assignment_building on assignment_building.buildingid = b.id join user on user.id = assignment_building.staffid ");
        if (params.containsKey("typeCodes")) sql.append(" join building_renttype on building_renttype.buildingid = b.id join renttype on renttype.id = building_renttype.renttypeid ");
    }
    public static void nomarlQuery(Map<String, String> params, List<String> typeCodes, StringBuilder where){
        for (var entry: params.entrySet()){
            String key = entry.getKey(), value = entry.getValue();
            if (key.equals("rentAreaFrom") || key.equals("rentAreaTo") || key.equals("typeCodes") || key.equals("rentPriceFrom") || key.equals("rentPriceTo") || key.equals("districtCode")) continue;
            if (value == null || value.isEmpty()) continue;
            if (NumberUtil.isNumber(value)){
                where.append(" and b." + key + " = " + value + " ");
            }
            else where.append(" and b." + key + " like '% " + value + "%' ");
        }
    }

    public static void specialQuery(Map<String, String> params, List<String> typeCodes, StringBuilder where){
        if (params.containsKey("districtCode") && !params.get("districtCode").isEmpty()){
            where.append(" and district.code = '" + params.get("districtCode") + "' ");
        }
        if (params.containsKey("rentAreaFrom") && !params.get("rentAreaFrom").isEmpty()){
            where.append(" and rent_area.value >= " + params.get("rentAreaFrom") + " ");
        }
        if (params.containsKey("rentAreaTo") && !params.get("rentAreaTo").isEmpty()){
            where.append(" and rent_area.value <= " + params.get("rentAreaTo") + " ");
        }
        if (params.containsKey("rentPriceFrom") && !params.get("rentPriceFrom").isEmpty()){
            where.append(" and b.rentprice >= " + params.get("rentPriceFrom") + " ");
        }
        if (params.containsKey("rentPriceTo") && !params.get("rentPriceTo").isEmpty()){
            where.append(" and b.rentprice <= " + params.get("rentPriceTo") + " ");
        }
        if (typeCodes != null && !typeCodes.isEmpty()){
            List tmp = new ArrayList();
            for (String code : typeCodes){
                String s = "'" + code + "'";
                tmp.add(s);
            }
            where.append(" and renttype.code in (" + String.join(", ", tmp) + ") ");
        }
    }

    @Override
    public List<BuildingEntity> searchBuilding(Map<String, String> params, List<String> typeCodes){
        StringBuilder sql = new StringBuilder("SELECT b.id, b.name, b.numberofbasement, b.districtid, b.street, b.ward, b.managername, b.managerphonenumber, b.floorarea, b.rentprice, b.servicefee, b.brokeragefee FROM building b ");
        joinProcess(params, typeCodes, sql);
        StringBuilder where =new StringBuilder( " WHERE 1 = 1 ");
        nomarlQuery(params, typeCodes, where);
        specialQuery(params, typeCodes, where);
        sql.append(where).append(" group by b.id");
        List<BuildingEntity> results = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batdongsan", "root", "582006")) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql.toString());
            while (rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getString("id"));
                buildingEntity.setDistrictID(rs.getString("districtid"));
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setManagerName(rs.getString("managername"));
                buildingEntity.setManagerPhoneNumber(rs.getString("managerphonenumber"));
                buildingEntity.setFloorArea(rs.getString("floorarea"));
                buildingEntity.setRentPrice(rs.getString("rentprice"));
                buildingEntity.setServiceFee(rs.getString("servicefee"));
                buildingEntity.setBrokerageFee(rs.getString("brokeragefee"));
                results.add(buildingEntity);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return results;
    }
}
