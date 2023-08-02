package com.nowij.groupware.controller;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/info")
    public List<EmployeeDto> employeeInfoList() {
        return service.employeeInfoList();
    }

}
