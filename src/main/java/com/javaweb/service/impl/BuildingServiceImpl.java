package com.javaweb.service.impl;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Override
    public List<BuildingDTO> find() {
        List<BuildingEntity> buildings = buildingRepository.find();
        List<BuildingDTO> result = new ArrayList<>();
        for (BuildingEntity building: buildings){
            BuildingDTO buildingDTO = new BuildingDTO();
            buildingDTO.setName(building.getName());
            buildingDTO.setNumberOfBasement(building.getNumberOfBasement());
            buildingDTO.setAddress(building.getStreet() + ", " + building.getWard());
            result.add(buildingDTO);
        }
        return result;
    }
}
