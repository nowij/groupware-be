package com.nowij.groupware.personal.dto;

import com.nowij.groupware.department.domain.DepartmentEntity;
import com.nowij.groupware.position.entity.PositionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonalDto {
    private String employeeId;
    private String userName;
    private String userPasswd;
    private String newPasswd;
    private String email;
    private String phone;
    private String address;
    private DepartmentEntity department;
    private byte[] image;
    private PositionEntity position;
}
