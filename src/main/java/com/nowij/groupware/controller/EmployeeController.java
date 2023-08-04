package com.nowij.groupware.controller;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<EmployeeDto> selectemployeeList() {
        return service.selectemployeeList();
    }

    @PostMapping("/mypage")
    public EmployeeDto selectMyPage(@RequestBody EmployeeDto dto) {
        return service.selectMyPage(dto.getEmployeeId());
    }

    @PostMapping("/mypage/update")
    public EmployeeDto updateMypage(@RequestBody EmployeeDto dto) {
        return service.updateMypage(dto);
    }
}
