package com.nowij.groupware.service.impl;

import com.nowij.groupware.dto.EmployeeDto;
import com.nowij.groupware.exception.ResourceNotFoundException;
import com.nowij.groupware.model.DepartmentEntity;
import com.nowij.groupware.model.EmployeeEntity;
import com.nowij.groupware.model.PositionEntity;
import com.nowij.groupware.repository.DepartmentRepository;
import com.nowij.groupware.repository.EmployeeRepository;
import com.nowij.groupware.repository.PositionRepository;
import com.nowij.groupware.service.EmployeeService;
import com.nowij.groupware.specification.EmployeeSpec;
import jakarta.transaction.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private PositionRepository positionRepository;
    //private PasswordEncoder encoder;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository
                               ,PositionRepository positionRepository
            //, PasswordEncoder encoder
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        //this.encoder = encoder;
    }

    @Override
    public List<EmployeeDto> selectEmployeeList() {
        List<EmployeeEntity> lists = employeeRepository.findAll();
        return lists.stream()
                .map(list -> entityToDto(list))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> selectEmployeeList(EmployeeDto dto) {
        Map<String, Object> searchMap = new HashMap<>();

        if (dto.getEmployeeId() != null) searchMap.put("employeeId", dto.getEmployeeId());
        if (dto.getUserName() != null) searchMap.put("userName", dto.getUserName());
        if (dto.getPosition() != null) searchMap.put("positionCode", dto.getPosition().getPositionCode());
        if (dto.getDepartment() != null) searchMap.put("deptCode", dto.getDepartment().getDeptCode());
        if (dto.getPhone() != null) searchMap.put("phone", dto.getPhone());
        if (dto.getActiveYn() != null) searchMap.put("activeYn", dto.getActiveYn());

        return employeeRepository.findAll(EmployeeSpec.searchEmployee(searchMap))
                .stream()
                .map(emp -> entityToDto(emp))
                .collect(Collectors.toList());
    }

    @Override
    public String employeeRegister(EmployeeDto dto) {
        //dto.setUserPasswd(encoder.encode(dto.getUserPasswd())); // 패스워드 인코딩
        EmployeeEntity employeeEntity = dtoToEntity(dto);
        employeeRepository.save(employeeEntity);
        return null;
    }

    @Override
    public EmployeeDto selectMyPage(String employeeId) {
        return entityToDto(employeeRepository.findByEmployeeId(employeeId));
    }

    @Override
    public EmployeeDto updateMypage(EmployeeDto dto) {
        EmployeeEntity entity = employeeRepository.findByEmployeeId(dto.getEmployeeId());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        // TODO 사진 업로드
        EmployeeDto updateDto = entityToDto(employeeRepository.save(entity));
        return updateDto;
    }

    private EmployeeDto entityToDto(EmployeeEntity entity) {
        EmployeeDto dto = new EmployeeDto();
        dto.setEmployeeId(entity.getEmployeeId());
        dto.setUserName(entity.getName());
        dto.setUserPasswd(entity.getPasswd());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAddress(entity.getAddress());
        dto.setImage(entity.getImage());
        dto.setActiveYn(entity.getActiveYn());
        dto.setDepartment(entity.getDepartment());
        dto.setPosition(entity.getPosition());
        return dto;
    }

    private EmployeeEntity dtoToEntity(EmployeeDto dto) {
        EmployeeEntity entity = new EmployeeEntity();

        entity.setEmployeeId(dto.getEmployeeId());
        entity.setPasswd(dto.getUserPasswd());
        entity.setName(dto.getUserName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        entity.setDepartment(departmentRepository.getById(dto.getDepartment().getDeptCode()));
        entity.setPosition(positionRepository.getById(dto.getPosition().getPositionCode()));
        return entity;
    }
}
