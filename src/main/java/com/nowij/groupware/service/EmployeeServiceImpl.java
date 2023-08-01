package com.nowij.groupware.service;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.model.Employee;
import com.nowij.groupware.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository repository;
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }
    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        Employee employee = new Employee();
        employee.setEmployeeId(dto.getEmployeeId());
        employee.setName(dto.getUserName());
        employee.setPasswd(dto.getUserPasswd());
        employee.setEmail(dto.getEmail());
        employee.setPhone(dto.getPhone());
        employee.setAddress(dto.getAddress());
        employee.setDeptCode(dto.getDeptCode());
        employee.setImage(dto.getImage());
        employee.setPositionCode(dto.getPositionCode());

        repository.save(employee);
        return null;
    }
}
