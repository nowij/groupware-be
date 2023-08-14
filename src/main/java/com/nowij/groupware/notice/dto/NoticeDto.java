package com.nowij.groupware.notice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class NoticeDto {
    private int noticeNo;
    private String title;
    private String content;
    private String employeeId;
    private String fixedYn;
    private Date firstDate;
}
