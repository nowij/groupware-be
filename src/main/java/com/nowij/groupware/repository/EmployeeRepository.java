package com.nowij.groupware.repository;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>, JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByEmployeeId(String employeeId);
    //EmployeeEntity findByEmployeeId(String employeeId);
    Boolean existsByEmployeeId(String employeeId);
}
