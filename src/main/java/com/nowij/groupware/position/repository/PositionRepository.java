package com.nowij.groupware.position.repository;

import com.nowij.groupware.position.dto.PositionDto;
import com.nowij.groupware.position.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<PositionEntity, String> {
    PositionDto findByPositionCode(String positionCode);
}
