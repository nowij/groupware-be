package com.nowij.groupware.model;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "DEPARTMENT")
public class DepartmentEntity {
    @Id
    @Column(name = "DEPT_CODE")
    @JoinColumn(name = "DEPT_CODE")
    private String deptCode;
    @Column(name = "DEPT_NAME")
    private String deptName;
}
