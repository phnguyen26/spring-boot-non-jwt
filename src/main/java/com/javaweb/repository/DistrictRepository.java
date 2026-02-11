package com.javaweb.repository;

import com.javaweb.entity.DistrictEntity;

public interface DistrictRepository {
    public DistrictEntity findNameById(Long id);
}
