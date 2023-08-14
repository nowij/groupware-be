package com.nowij.groupware.entity;

import jakarta.persistence.*;
import lombok.Data;
@Data
@Entity
@Table(name = "department")
public class DepartmentEntity {
    @Id
    private String deptCode;
    private String deptName;
}
