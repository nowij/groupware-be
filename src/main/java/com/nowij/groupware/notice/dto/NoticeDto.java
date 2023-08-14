package com.nowij.groupware.notice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NoticeDto {
    private int noticeNo;
    private String title;
    private String content;
    private String employeeId;
    private String employeeName;
    private String fixedYn;
    private String firstDate;
}
