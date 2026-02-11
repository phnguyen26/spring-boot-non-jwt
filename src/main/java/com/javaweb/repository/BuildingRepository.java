package com.javaweb.repository;

import com.javaweb.builder.SearchBuildingBuilder;
import com.javaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> searchBuilding(SearchBuildingBuilder searchBuildingBuilder);
}
