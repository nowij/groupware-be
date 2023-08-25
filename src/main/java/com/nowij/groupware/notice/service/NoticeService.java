package com.nowij.groupware.notice.service;

import com.nowij.groupware.comm.dto.PageDto;
import com.nowij.groupware.comm.dto.PageResponseDto;
import com.nowij.groupware.notice.dto.NoticeDto;

import java.util.List;

public interface NoticeService {
    PageResponseDto selectNoticeList(PageDto dto);
    void saveNotice(NoticeDto dto);
    NoticeDto selectNoticeContent(int no);
    NoticeDto updateNoticeContent(NoticeDto dto);
    int deleteNotice(int no);
}
