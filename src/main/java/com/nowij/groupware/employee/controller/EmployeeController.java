package com.nowij.groupware.employee.controller;

import com.nowij.groupware.comm.dto.PageDto;
import com.nowij.groupware.comm.dto.PageResponseDto;
import com.nowij.groupware.employee.dto.EmployeeDto;
import com.nowij.groupware.employee.service.EmployeeService;
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

//    public List<EmployeeDto> selectEmployeeList() {
//        return service.selectEmployeeList();
//    }
    @PostMapping("")
    public PageResponseDto selectEmployeeList(@RequestBody PageDto dto) {
        return service.selectEmployeeList(dto);
    }

    @PostMapping("/search")
    public List<EmployeeDto> selectEmployeeList(@RequestBody EmployeeDto dto) {
        return service.selectEmployeeList(dto);
    }

    @GetMapping("/newId")
    public String selectNewEmployeeId() {
        return service.selectNewEmployeeId();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeId(@PathVariable("id") String id) {
        service.deleteEmployeeId(id);
        return new ResponseEntity<>("Success Delete!", HttpStatus.OK);
    }

    @PutMapping("/save/{id}")
    public ResponseEntity<String> saveInfoOnByAdmin(@RequestBody EmployeeDto dto) {
        service.saveInfoOnByAdmin(dto);
        return new ResponseEntity<>("Success Update!", HttpStatus.OK);
    }
}


