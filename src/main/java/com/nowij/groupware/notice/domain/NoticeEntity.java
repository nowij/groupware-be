package com.nowij.groupware.notice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "notice")
public class NoticeEntity {
    @Id
    private int noticeNo;
    private String title;
    private String content;
    private String employeeId;
    private String fixedYn;
    private Date firstDate;
    private Date modifyDate;
}
