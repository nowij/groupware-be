package com.nowij.groupware.repository;

import com.nowij.groupware.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String> {
    Optional<EmployeeEntity> findByEmployeeId(String employeeId);
    Boolean existsByEmployeeId(String employeeId);
}
