package com.nowij.groupware.repository;

import com.nowij.groupware.dto.PositionDto;
import com.nowij.groupware.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, String> {
    PositionDto findByPositionCode(String positionCode);
}
