package com.nowij.groupware.controller;

import com.nowij.groupware.dto.CommonDto;
import com.nowij.groupware.service.CommonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cmm")
public class CommonController {
    private CommonService service;

    public CommonController(CommonService service) {
        this.service = service;
    }

    @RequestMapping("/department")
    public List<CommonDto> selectDeptList() {
        return service.selectDeptList();
    }

    @RequestMapping("/position")
    public List<CommonDto> selectPositList() {
        return service.selectPositList();
    }
}
