package com.nowij.groupware.dto;


import com.nowij.groupware.entity.DepartmentEntity;
import com.nowij.groupware.entity.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
