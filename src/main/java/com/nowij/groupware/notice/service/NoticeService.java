package com.nowij.groupware.notice.service;

import com.nowij.groupware.notice.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    List<NoticeDto> selectNoticeList();
}
