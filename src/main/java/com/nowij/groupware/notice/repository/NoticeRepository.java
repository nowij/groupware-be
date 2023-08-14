package com.nowij.groupware.notice.repository;

import com.nowij.groupware.notice.domain.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer> {

}
