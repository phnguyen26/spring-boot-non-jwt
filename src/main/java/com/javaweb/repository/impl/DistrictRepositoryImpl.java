package com.javaweb.repository.impl;

import com.javaweb.entity.DistrictEntity;
import com.javaweb.repository.DistrictRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
    @Override
    public DistrictEntity findNameById(String id) {
        String sql = "select d.name from district d where d.id = " + id;
        DistrictEntity districtEntity = new DistrictEntity();
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batdongsan", "root", "582006")){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                districtEntity.setName(rs.getString("name"));
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return districtEntity;
    }
}
