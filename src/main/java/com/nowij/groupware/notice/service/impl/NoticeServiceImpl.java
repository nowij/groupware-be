package com.nowij.groupware.notice.service.impl;

import com.nowij.groupware.comm.dto.PageDto;
import com.nowij.groupware.comm.dto.PageResponseDto;
import com.nowij.groupware.employee.domain.EmployeeEntity;
import com.nowij.groupware.employee.repository.EmployeeRepository;
import com.nowij.groupware.notice.domain.NoticeEntity;
import com.nowij.groupware.notice.dto.NoticeDto;
import com.nowij.groupware.notice.repository.NoticeRepository;
import com.nowij.groupware.notice.service.NoticeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PageResponseDto selectNoticeList(PageDto dto) {
        int start = dto.getPageNo();
        int end = dto.getPageSize();
        Pageable pageable = PageRequest.of(start, end, Sort.by("fixedYn", "noticeNo").descending());
        Page<NoticeEntity> notices = noticeRepository.findAll(pageable);
        List<NoticeEntity> listOfIndex = notices.getContent();
        List<NoticeDto> content = listOfIndex.stream().map(m -> entityToDto(m)).collect(Collectors.toList());

        PageResponseDto result = new PageResponseDto();
        result.setContent(content);
        result.setPageNo(notices.getNumber());
        result.setPageSize(notices.getSize());
        result.setTotalElements(notices.getTotalElements());
        result.setTotalPages(notices.getTotalPages());
        result.setLast(notices.isLast());
        return result;
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

    @Override
    public NoticeDto selectNoticeContent(int no) {
        Optional<NoticeEntity> optional = noticeRepository.findById(no);
        if (optional.isPresent()) {
            return entityToDto(optional.get());
        }
        return null;
    }

    @Override
    public NoticeDto updateNoticeContent(NoticeDto dto) {
        Optional<NoticeEntity> optional = noticeRepository.findById(dto.getNoticeNo());
        if (optional.isPresent()) {
            NoticeEntity entity = optional.get();
            entity.setContent(dto.getContent());
            entity.setTitle(dto.getContent());
            entity.setFixedYn(dto.getFixedYn());
            return entityToDto(noticeRepository.save(entity));
        }
        return null;
    }

    @Override
    public int deleteNotice(int no) {
        Optional<NoticeEntity> optional = noticeRepository.findById(no);
        if (optional.isPresent()) {
            noticeRepository.delete(optional.get());
            return 1;
        }
        return 0;
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
