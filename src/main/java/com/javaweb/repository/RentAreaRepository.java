package com.javaweb.repository;

import com.javaweb.entity.RentAreaEntity;

public interface RentAreaRepository {
    public RentAreaEntity findValueByBuildingId(Long id);
}
