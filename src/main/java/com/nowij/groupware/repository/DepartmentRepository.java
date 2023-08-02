package com.nowij.groupware.repository;

import com.nowij.groupware.model.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {
    Optional<DepartmentEntity> findByDeptCode(String deptCode);
}
