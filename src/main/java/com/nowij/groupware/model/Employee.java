package com.nowij.groupware.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
    @Id
    private String employeeId;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_passwd")
    private String passwd;
    private String email;
    private String phone;
    private String address;
    private String deptCode;
    private byte[] image;
    private String activeYn;
    private String positionCode;
}
