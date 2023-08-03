package com.nowij.groupware.service.impl;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.exception.ResourceNotFoundException;
import com.nowij.groupware.model.DepartmentEntity;
import com.nowij.groupware.model.EmployeeEntity;
import com.nowij.groupware.repository.DepartmentRepository;
import com.nowij.groupware.repository.EmployeeRepository;
import com.nowij.groupware.service.EmployeeService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private PasswordEncoder encoder;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository, PasswordEncoder encoder) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.encoder = encoder;
    }

    @Override
    public List<EmployeeDto> employeeInfoList() {
        List<EmployeeEntity> lists = employeeRepository.findAll();
        return lists.stream()
                .map(list -> mapToDto(list))
                .collect(Collectors.toList());
    }

    @Override
    public String employeeRegister(EmployeeDto dto) {
        dto.setUserPasswd(encoder.encode(dto.getUserPasswd())); // 패스워드 인코딩
        EmployeeEntity employeeEntity = dtoToEntity(dto);
        employeeRepository.save(employeeEntity);
        return null;
    }


    private EmployeeDto mapToDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setUserName(entity.getName());
        dto.setUserPasswd(entity.getPasswd());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setDeptCode(entity.getDepartment().getDeptCode());
        dto.setImage(entity.getImage());
        dto.setActiveYn(entity.getActiveYn());
        dto.setPositionCode(entity.getPositionCode());
        return dto;
    }

    private EmployeeEntity dtoToEntity(EmployeeDto dto) {
        EmployeeEntity entity = new EmployeeEntity();
        DepartmentEntity department = new DepartmentEntity();
        department.setDeptCode(dto.getDeptCode());
        department.setDeptName(departmentRepository.findByDeptName(dto.getDeptCode()));

        entity.setEmployeeId(dto.getEmployeeId());
        entity.setPasswd(dto.getUserPasswd());
        entity.setName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setDepartment(department);
        entity.setPositionCode(dto.getPositionCode());
        return entity;
    }
}
