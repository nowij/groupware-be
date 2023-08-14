package com.nowij.groupware.comm.service.impl;

import com.nowij.groupware.comm.dto.CommonDto;
import com.nowij.groupware.department.domain.DepartmentEntity;
import com.nowij.groupware.position.entity.PositionEntity;
import com.nowij.groupware.department.repository.DepartmentRepository;
import com.nowij.groupware.position.repository.PositionRepository;
import com.nowij.groupware.comm.service.CommonService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements CommonService {
    private DepartmentRepository departmentRepository;
    private PositionRepository positionRepository;

    public CommonServiceImpl(DepartmentRepository departmentRepository, PositionRepository positionRepository) {
        this.departmentRepository = departmentRepository;
        this.positionRepository = positionRepository;
    }
    @Override
    public List<CommonDto> selectDeptList() {
        return departmentRepository.findAll()
                .stream()
                .map(ett -> entityToDto(ett))
                .collect(Collectors.toList());
    }

    @Override
    public List<CommonDto> selectPositList() {
        return positionRepository.findAll()
                .stream()
                .map(ett -> entityToDto(ett))
                .collect(Collectors.toList());
    }

    private CommonDto entityToDto(DepartmentEntity entity) {
        CommonDto dto = new CommonDto();
        dto.setDeptCode(entity.getDeptCode());
        dto.setDeptName(entity.getDeptName());
        return dto;
    }

    private CommonDto entityToDto(PositionEntity entity) {
        CommonDto dto = new CommonDto();
        dto.setPositCode(entity.getPositionCode());
        dto.setPositName(entity.getPositionName());
        return dto;
    }
}
