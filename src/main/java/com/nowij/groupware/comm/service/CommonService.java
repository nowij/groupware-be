package com.nowij.groupware.comm.service;

import com.nowij.groupware.comm.dto.CommonDto;

import java.util.List;

public interface CommonService {
    List<CommonDto> selectDeptList();
    List<CommonDto> selectPositList();
}
