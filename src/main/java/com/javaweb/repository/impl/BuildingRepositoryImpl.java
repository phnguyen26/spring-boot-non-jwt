package com.javaweb.repository.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Service
public class BuildingRepositoryImpl  implements BuildingRepository {
    String sql = "SELECT * FROM building";

    @Override
    public List<BuildingEntity> find() {
        List<BuildingEntity> buildings = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/batdongsan", "root", "582006")){
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                BuildingEntity buildingentity = new BuildingEntity();
                buildingentity.setName(rs.getString("name"));
                buildingentity.setWard(rs.getString("ward"));
                buildingentity.setStreet(rs.getString("street"));
                buildingentity.setNumberOfBasement(rs.getInt("NumberOfBasement"));
                buildings.add(buildingentity);
            }
        }catch(Exception e){
            System.out.println( e.getMessage());
        }
        return buildings;
    }
}
