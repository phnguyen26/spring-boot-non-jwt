package com.javaweb.service;

import com.javaweb.DTO.BuildingDTO;

import java.util.List;
import java.util.Map;

public interface BuildingService {
   List<BuildingDTO> searchBuilding(Map<String, String> params, List<String> typeCodes);
}
