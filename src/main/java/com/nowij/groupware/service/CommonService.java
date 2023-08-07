package com.nowij.groupware.service;

import com.nowij.groupware.dto.CommonDto;
import com.nowij.groupware.dto.DepartmentDto;
import com.nowij.groupware.dto.PositionDto;

import java.util.List;

public interface CommonService {
    List<CommonDto> selectDeptList();
    List<CommonDto> selectPositList();
}
