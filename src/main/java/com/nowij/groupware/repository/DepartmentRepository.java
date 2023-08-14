package com.nowij.groupware.repository;

import com.nowij.groupware.dto.DepartmentDto;
import com.nowij.groupware.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {
    DepartmentDto findByDeptCode(String deptCode);
}
