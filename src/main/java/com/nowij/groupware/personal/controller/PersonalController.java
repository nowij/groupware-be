package com.nowij.groupware.personal.controller;

import com.nowij.groupware.employee.dto.EmployeeDto;
import com.nowij.groupware.personal.dto.PersonalDto;
import com.nowij.groupware.personal.service.PersonalService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/personal")
public class PersonalController {
    private PersonalService service;

    public PersonalController(PersonalService service) {
        this.service = service;
    }

    @PostMapping("/info")
    public PersonalDto selectInfo(@RequestBody PersonalDto dto) {
        return service.selectInfo(dto.getEmployeeId());
    }

    @PutMapping("/info/save")
    public PersonalDto saveInfo(@RequestBody PersonalDto dto) {
        return service.saveInfo(dto);
    }

    @PutMapping("/savePwd")
    public ResponseEntity<String> savePwd(@RequestBody PersonalDto dto) {
        int result = service.savePwd(dto);
        if (result == 0) {
            return new ResponseEntity<>("Error Change Password", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Success Change Password", HttpStatus.OK);
    }
}
