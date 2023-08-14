package com.nowij.groupware.department.repository;

import com.nowij.groupware.department.dto.DepartmentDto;
import com.nowij.groupware.department.domain.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, String> {
    DepartmentDto findByDeptCode(String deptCode);
}
