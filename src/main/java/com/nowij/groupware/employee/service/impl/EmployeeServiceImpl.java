package com.nowij.groupware.employee.service.impl;

import com.nowij.groupware.employee.dto.EmployeeDto;
import com.nowij.groupware.employee.domain.EmployeeEntity;
import com.nowij.groupware.department.repository.DepartmentRepository;
import com.nowij.groupware.employee.repository.EmployeeRepository;
import com.nowij.groupware.position.repository.PositionRepository;
import com.nowij.groupware.employee.service.EmployeeService;
import com.nowij.groupware.specification.EmployeeSpec;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private PositionRepository positionRepository;
    private PasswordEncoder encoder;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository
                               ,PositionRepository positionRepository
            , PasswordEncoder encoder
    ) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
        this.encoder = encoder;
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
        if (!StringUtils.isEmpty(dto.getEmployeeId())) searchMap.put("employeeId", dto.getEmployeeId());
        if (!StringUtils.isEmpty(dto.getUserName())) searchMap.put("name", dto.getUserName());
        if (!StringUtils.isEmpty(dto.getPosition().getPositionCode())) searchMap.put("position", dto.getPosition().getPositionCode());
        if (!StringUtils.isEmpty(dto.getDepartment().getDeptCode())) searchMap.put("department", dto.getDepartment().getDeptCode());
        if (!StringUtils.isEmpty(dto.getPhone())) searchMap.put("phone", dto.getPhone());
        if (!StringUtils.isEmpty(dto.getActiveYn())) searchMap.put("activeYn", dto.getActiveYn());

        return employeeRepository.findAll(EmployeeSpec.searchEmployee(searchMap))
                .stream()
                .map(emp -> entityToDto(emp))
                .collect(Collectors.toList());
    }

    @Override
    public String employeeRegister(EmployeeDto dto) {
        dto.setUserPasswd(encoder.encode(dto.getUserPasswd())); // 패스워드 인코딩
        EmployeeEntity employeeEntity = dtoToEntity(dto);
        employeeRepository.save(employeeEntity);
        return null;
    }

    @Override
    public String selectNewEmployeeId() {
        return employeeRepository.selectNewEmployeeId();
    }

    @Override
    public EmployeeDto selectMyPage(String employeeId) {
        return entityToDto(employeeRepository.findById(employeeId).get());
    }

    @Override
    public EmployeeDto updateMypage(EmployeeDto dto) {
        EmployeeEntity entity = employeeRepository.findByEmployeeId(dto.getEmployeeId()).get();
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
        dto.setJoinDate(entity.getJoinDate());
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
        entity.setActiveYn(dto.getActiveYn());
        entity.setDepartment(departmentRepository.getById(dto.getDepartment().getDeptCode()));
        entity.setPosition(positionRepository.getById(dto.getPosition().getPositionCode()));
        entity.setJoinDate(dto.getJoinDate());
        return entity;
    }
}
