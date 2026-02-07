package com.javaweb.service.impl;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.DistrictEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private DistrictRepository districtRepository;
    @Autowired
    private RentAreaRepository rentAreaRepository;

    @Override
    public List<BuildingDTO> searchBuilding(Map<String, String> params, List<String> typeCodes) {
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuilding(params, typeCodes);
        List<BuildingDTO> results = new ArrayList<>();
        for (BuildingEntity buildingEntity: buildingEntities){
            BuildingDTO buildingDTO = new BuildingDTO();
            DistrictEntity districtEntity = districtRepository.findNameById(buildingEntity.getDistrictID());
            RentAreaEntity  rentAreaEntity = rentAreaRepository.findValueByBuildingId(buildingEntity.getId());
            buildingDTO.setName(buildingEntity.getName());
            buildingDTO.setAddress(String.join(", ", buildingEntity.getStreet(), buildingEntity.getWard(), districtEntity.getName()));
            buildingDTO.setNumberOfBasement(buildingEntity.getNumberOfBasement());
            buildingDTO.setManagerName(buildingEntity.getManagerName());
            buildingDTO.setManagerPhoneNumber(buildingEntity.getManagerPhoneNumber());
            buildingDTO.setFloorArea(buildingEntity.getFloorArea());
            buildingDTO.setRentPrice(buildingEntity.getRentPrice());
            buildingDTO.setServiceFee(buildingEntity.getServiceFee());
            buildingDTO.setBrokerageFee(buildingEntity.getBrokerageFee());
            buildingDTO.setRenArea(rentAreaEntity.getValue());
            results.add(buildingDTO);
        }
        return results;
    }
}
