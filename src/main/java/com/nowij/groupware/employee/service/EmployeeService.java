package com.nowij.groupware.employee.service;

import com.nowij.groupware.comm.dto.PageDto;
import com.nowij.groupware.comm.dto.PageResponseDto;
import com.nowij.groupware.employee.domain.EmployeeEntity;
import com.nowij.groupware.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    PageResponseDto selectEmployeeList(PageDto dto);
    List<EmployeeDto> selectEmployeeList(EmployeeDto dto);
    String employeeRegister(EmployeeDto dto);
    String selectNewEmployeeId();
    void deleteEmployeeId(String id);
    EmployeeEntity getExistEmployee(String id);
    EmployeeDto saveInfoOnByAdmin(EmployeeDto dto);
}
