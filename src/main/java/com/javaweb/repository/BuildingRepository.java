package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

public interface BuildingRepository {
    List<BuildingEntity> searchBuilding(Map<String, String> params, List<String> typeCodes);
}
