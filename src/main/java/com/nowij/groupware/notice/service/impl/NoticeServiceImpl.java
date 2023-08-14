package com.nowij.groupware.notice.service.impl;

import com.nowij.groupware.notice.dto.NoticeDto;
import com.nowij.groupware.notice.repository.NoticeRepository;
import com.nowij.groupware.notice.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {
    private NoticeRepository repository;

    public NoticeServiceImpl(NoticeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<NoticeDto> selectNoticeList() {
        return null;
    }
}
