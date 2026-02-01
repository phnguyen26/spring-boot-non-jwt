package com.javaweb.repository;

import com.javaweb.entity.BuildingEntity;

import java.util.List;

public interface BuildingRepository {
    List<BuildingEntity> find();
}
