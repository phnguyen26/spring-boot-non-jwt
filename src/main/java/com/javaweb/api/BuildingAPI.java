package com.javaweb.api;
import com.javaweb.DTO.BuildingDTO;
import com.javaweb.customException.RequiredFieldException;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BuildingAPI {
//	public void check(BuildingDTO buildingDTO){
//		RequiredFieldException error = new RequiredFieldException("Invalid input!");
//		Map<String, String> details = new HashMap<>();
//		if (buildingDTO.getName() == null || buildingDTO.getName().isEmpty()) details.put("name", error.getDefaultDetailMessage());
//		if (buildingDTO.getNumberOfBasement() == null) details.put("numberOfBasement", error.getDefaultDetailMessage());
//		error.setErrorDetails(details);
//		if (!details.isEmpty()){
//			throw error;
//		}
//	}
//	@PostMapping("/building")
//	public Object addBuilding(@RequestBody BuildingDTO buildingDTO){
//		check(buildingDTO);
//		return "Add building successfully!";
//	}
	@Autowired
	BuildingService buildingService;
	@GetMapping("/building")
	public List<BuildingDTO> searchBuilding(@RequestParam Map<String, String> params,
								 @RequestParam(value = "typeCodes", required = false) List<String> typeCodes)
		{
		List<BuildingDTO> result = buildingService.searchBuilding(params, typeCodes);
		return result;
	}

}
