package com.javaweb.service.impl;

import com.javaweb.DTO.BuildingDTO;
import com.javaweb.builder.SearchBuildingBuilder;
import com.javaweb.converter.SearchBuildingBuilderConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.DistrictEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.BuildingService;
import org.modelmapper.ModelMapper;
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
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SearchBuildingBuilderConverter searchBuildingBuilderConverter;
    @Override
    public List<BuildingDTO> searchBuilding(Map<String, Object> params, List<String> typeCodes) {
        SearchBuildingBuilder searchBuildingBuilder = searchBuildingBuilderConverter.toSearchBuildingBuilder(params, typeCodes);
        List<BuildingEntity> buildingEntities = buildingRepository.searchBuilding(searchBuildingBuilder);
        List<BuildingDTO> results = new ArrayList<>();
        for (BuildingEntity buildingEntity: buildingEntities){
            BuildingDTO buildingDTO =modelMapper.map(buildingEntity, BuildingDTO.class);
            DistrictEntity districtEntity = districtRepository.findNameById(buildingEntity.getDistrictId());
            RentAreaEntity  rentAreaEntity = rentAreaRepository.findValueByBuildingId(buildingEntity.getId());
            buildingDTO.setAddress(String.join(", ", buildingEntity.getStreet(), buildingEntity.getWard(), districtEntity.getName()));
            buildingDTO.setRenArea(rentAreaEntity.getValue());
            results.add(buildingDTO);
        }
        return results;
    }
}
