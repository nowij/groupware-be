package com.nowij.groupware.employee.domain;

import com.nowij.groupware.department.domain.DepartmentEntity;
import com.nowij.groupware.position.entity.PositionEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "employee")
public class EmployeeEntity {
    @Id
    private String employeeId;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_passwd")
    private String passwd;
    private String email;
    private String phone;
    private String address;
    @ManyToOne
    @JoinColumn(name = "dept_code")
    private DepartmentEntity department;
    private byte[] image;
    @ColumnDefault("N")
    private String activeYn;
    @ManyToOne
    @JoinColumn(name = "position_code")
    private PositionEntity position;
    private Date joinDate;
}
