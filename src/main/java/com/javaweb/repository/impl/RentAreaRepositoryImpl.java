package com.javaweb.repository.impl;

import com.javaweb.entity.DistrictEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.RentAreaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@Repository
public class RentAreaRepositoryImpl implements RentAreaRepository {
    @Override
    public RentAreaEntity findValueByBuildingId(Long id) {
        String sql = "select group_concat(r.value) as value from rent_area r where r.buildingid = " + id;
        RentAreaEntity rentAreaEntity = new RentAreaEntity();
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batdongsan", "root", "582006")){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                rentAreaEntity.setValue(rs.getString("value"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rentAreaEntity;
    }
}
