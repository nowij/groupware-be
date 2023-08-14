package com.nowij.groupware.repository;

import com.nowij.groupware.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, String>, JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByEmployeeId(String employeeId);
    //EmployeeEntity findByEmployeeId(String employeeId);
    Boolean existsByEmployeeId(String employeeId);

    @Query(value = "SELECT CONCAT('GW', LPAD(MAX(SUBSTRING(EMPLOYEE_ID, 5, 2))+1, 4, 0)) FROM employee", nativeQuery = true)
    String selectNewEmployeeId();
}
