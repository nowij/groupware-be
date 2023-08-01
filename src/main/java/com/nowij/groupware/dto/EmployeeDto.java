package com.nowij.groupware.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String deptCode;
    private byte[] image;
    private String activeYn;
    private String positionCode;
}
