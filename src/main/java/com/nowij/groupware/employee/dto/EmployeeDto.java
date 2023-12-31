package com.nowij.groupware.employee.dto;


import com.nowij.groupware.department.domain.DepartmentEntity;
import com.nowij.groupware.position.entity.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private String employeeId;
    private String userName;
    private String userPasswd;
    private String email;
    private String phone;
    private String address;
    private DepartmentEntity department;
    private byte[] image;
    private String activeYn;
    private PositionEntity position;
    private Date joinDate;
}
