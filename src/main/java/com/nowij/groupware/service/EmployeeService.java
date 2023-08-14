package com.nowij.groupware.service;

import com.nowij.groupware.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> selectEmployeeList();
    List<EmployeeDto> selectEmployeeList(EmployeeDto dto);
    String employeeRegister(EmployeeDto dto);
    String selectNewEmployeeId();
    EmployeeDto selectMyPage(String employeeId);
    EmployeeDto updateMypage(EmployeeDto dto);
}
