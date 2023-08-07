package com.nowij.groupware.dto;

import lombok.Data;

@Data
public class AuthDto {
    private String token;
    private String tokenType = "Bearer ";
    private String employeeId;
    private String deptCode;
    private String userName;

    public AuthDto(String accessToken) {
        this.token = accessToken;
    }
}
