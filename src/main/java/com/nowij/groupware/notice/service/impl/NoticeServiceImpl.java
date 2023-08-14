package com.nowij.groupware.notice.service.impl;

import com.nowij.groupware.employee.domain.EmployeeEntity;
import com.nowij.groupware.employee.repository.EmployeeRepository;
import com.nowij.groupware.notice.domain.NoticeEntity;
import com.nowij.groupware.notice.dto.NoticeDto;
import com.nowij.groupware.notice.repository.NoticeRepository;
import com.nowij.groupware.notice.service.NoticeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

@Service
public class NoticeServiceImpl implements NoticeService {
    private NoticeRepository noticeRepository;
    private EmployeeRepository employeeRepository;

    public NoticeServiceImpl(NoticeRepository noticeRepository, EmployeeRepository employeeRepository) {
        this.noticeRepository = noticeRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<NoticeDto> selectNoticeList() {
        List<NoticeEntity> lists = noticeRepository.findAll(Sort.by(Sort.Direction.DESC, "fixedYn", "noticeNo"));
        return lists.stream()
                .map(list ->entityToDto(list))
                .collect(Collectors.toList());
    }

    @Override
    public void saveNotice(NoticeDto dto) {
        NoticeEntity entity = new NoticeEntity();
        Date date = new Date();
        entity.setTitle(dto.getTitle());
        entity.setContent(dto.getContent());
        entity.setEmployeeId(dto.getEmployeeId());
        entity.setFixedYn(dto.getFixedYn());
        noticeRepository.save(entity);
    }

    public NoticeDto entityToDto(NoticeEntity entity) {
        NoticeDto dto = new NoticeDto();
        dto.setNoticeNo(entity.getNoticeNo());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setFirstDate(entity.getFirstDate().format(DateTimeFormatter.ISO_DATE));
        dto.setFixedYn(entity.getFixedYn());
        String name = searchEmployeeName(entity.getEmployeeId());
        if (!"".equals(name)) {
            dto.setEmployeeName(name);
        }
        return dto;
    }

    public String searchEmployeeName(String id) {
        Optional<EmployeeEntity> optional = employeeRepository.findByEmployeeId(id);
        if (optional.isPresent()) {
            EmployeeEntity entity = optional.get();
            return entity.getName();
        }
        return "";
    }
}
