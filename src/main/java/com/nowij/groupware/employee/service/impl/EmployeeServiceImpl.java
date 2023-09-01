package com.nowij.groupware.employee.service.impl;

import com.nowij.groupware.comm.dto.PageDto;
import com.nowij.groupware.comm.dto.PageResponseDto;
import com.nowij.groupware.employee.dto.EmployeeDto;
import com.nowij.groupware.employee.domain.EmployeeEntity;
import com.nowij.groupware.department.repository.DepartmentRepository;
import com.nowij.groupware.employee.repository.EmployeeRepository;
import com.nowij.groupware.exception.ResourceNotFoundException;
import com.nowij.groupware.position.repository.PositionRepository;
import com.nowij.groupware.employee.service.EmployeeService;
import com.nowij.groupware.specification.EmployeeSpec;
import io.micrometer.common.util.StringUtils;
import jakarta.transaction.Transactional;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public PageResponseDto selectEmployeeList(PageDto dto) {
        int start = dto.getPageNo();
        int end = dto.getPageSize();
        Map<String, Object> searchMap = new HashMap<>();
        searchMap.put("activeYn", "Y");
        Pageable pageable = PageRequest.of(start, end, Sort.by("employeeId").ascending());
        Page<EmployeeEntity> employees = employeeRepository.findAll(EmployeeSpec.searchEmployee(searchMap), pageable);
        List<EmployeeEntity> listOfIndex = employees.getContent();
        List<EmployeeDto> content = listOfIndex.stream().map(m -> entityToDto(m)).collect(Collectors.toList());
        PageResponseDto result = new PageResponseDto();
        result.setContent(content);
        result.setTotalElements(employees.getTotalElements());
        result.setPageNo(employees.getNumber());
        result.setPageSize(employees.getSize());
        result.setTotalPages(employees.getTotalPages());
        result.setLast(employees.isLast());
        return result;
    }


    @Override
    public PageResponseDto selectSpecEmployee(EmployeeDto dto) {
        Map<String, Object> searchMap = new HashMap<>();
        if (!StringUtils.isEmpty(dto.getEmployeeId())) searchMap.put("employeeId", dto.getEmployeeId());
        if (!StringUtils.isEmpty(dto.getUserName())) searchMap.put("name", dto.getUserName());
        if (!StringUtils.isEmpty(dto.getPosition().getPositionCode())) searchMap.put("position", dto.getPosition().getPositionCode());
        if (!StringUtils.isEmpty(dto.getDepartment().getDeptCode())) searchMap.put("department", dto.getDepartment().getDeptCode());
        if (!StringUtils.isEmpty(dto.getPhone())) searchMap.put("phone", dto.getPhone());
        if (!StringUtils.isEmpty(dto.getActiveYn())) searchMap.put("activeYn", dto.getActiveYn());

        employeeRepository.findAll(EmployeeSpec.searchEmployee(searchMap));
        List<EmployeeDto> content = employeeRepository.findAll(EmployeeSpec.searchEmployee(searchMap))
                .stream()
                .map(emp -> entityToDto(emp))
                .collect(Collectors.toList());

        PageResponseDto result = new PageResponseDto();
        result.setContent(content);
        result.setTotalElements(content.size());
        return result;
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
    public void deleteEmployeeId(String id) {
        EmployeeEntity employee = getExistEmployee(id);
        Date date = new Date();
        employee.setActiveYn("N");
        employee.setLeaveDate(date);
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity getExistEmployee(String id) {
        EmployeeEntity entity = employeeRepository.findByEmployeeId(id).orElseThrow(() ->
            new ResourceNotFoundException("Employee could not be found"));
        return entity;
    }

    @Override
    public EmployeeDto saveInfoOnByAdmin(EmployeeDto dto) {
        EmployeeEntity entity = getExistEmployee(dto.getEmployeeId());
        entity.setName(dto.getUserName());
        entity.setDepartment(dto.getDepartment());
        entity.setPosition(dto.getPosition());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(dto.getAddress());
        return entityToDto(employeeRepository.save(entity));
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
