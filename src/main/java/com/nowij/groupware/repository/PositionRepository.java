package com.nowij.groupware.repository;

import com.nowij.groupware.dto.PositionDto;
import com.nowij.groupware.model.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<PositionEntity, String> {
    PositionDto findByPositionCode(String positionCode);
}
