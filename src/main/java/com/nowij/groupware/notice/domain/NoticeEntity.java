package com.nowij.groupware.notice.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
@Table(name = "notice")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int noticeNo;
    private String title;
    private String content;
    private String employeeId;
    private String fixedYn;
    private LocalDateTime firstDate;
    private LocalDateTime modifyDate;

    @PrePersist
    public void prepersist() {
        if (this.firstDate == null)
            firstDate = LocalDateTime.now();

        if (this.modifyDate == null)
            modifyDate = LocalDateTime.now();
    }
}
