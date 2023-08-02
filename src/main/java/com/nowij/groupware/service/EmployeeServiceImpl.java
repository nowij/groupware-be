package com.nowij.groupware.service;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.exception.ResourceNotFoundException;
import com.nowij.groupware.model.EmployeeEntity;
import com.nowij.groupware.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository repository;
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<EmployeeDto> employeeInfoList() {
        List<EmployeeEntity> lists = repository.findAll();
        return lists.stream()
                .map(list -> mapToDto(list))
                .collect(Collectors.toList());
    }

    private EmployeeDto mapToDto(EmployeeEntity employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(employee.getEmployeeId());
        dto.setUserName(employee.getName());
        dto.setUserPasswd(employee.getPasswd());
        dto.setEmail(employee.getEmail());
        dto.setPhone(employee.getPhone());
        dto.setAddress(employee.getAddress());
        dto.setDeptCode(employee.getDeptCode());
        dto.setImage(employee.getImage());
        dto.setActiveYn(employee.getActiveYn());
        dto.setPositionCode(employee.getPositionCode());
        return dto;
    }
}
