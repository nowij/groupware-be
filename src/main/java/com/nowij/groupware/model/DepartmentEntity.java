package com.nowij.groupware.model;

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
