package com.nowij.groupware.notice.controller;

import com.nowij.groupware.notice.dto.NoticeDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    @RequestMapping("")
    public List<NoticeDto> selectNoticeList() {
        return null;
    }
}
