package com.nowij.groupware.notice.controller;

import com.nowij.groupware.notice.dto.NoticeDto;
import com.nowij.groupware.notice.service.NoticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notice")
public class NoticeController {

    private NoticeService service;

    public NoticeController(NoticeService service) {
        this.service = service;
    }
    @RequestMapping("")
    public List<NoticeDto> selectNoticeList() {
        return service.selectNoticeList();
    }

    @RequestMapping("/save")
    public ResponseEntity<String> saveNotice(@RequestBody NoticeDto dto) {
        service.saveNotice(dto);
        return new ResponseEntity<>("notice save", HttpStatus.OK);
    }

    @RequestMapping("/content/{no}")
    public NoticeDto selectNoticeContent(@PathVariable("no") int no) {
        return service.selectNoticeContent(no);
    }

    @RequestMapping("/content/update")
    public NoticeDto updateNoticeContent(@RequestBody NoticeDto dto) {
        return service.updateNoticeContent(dto);
    }
    @RequestMapping("/delete")
    public ResponseEntity<String> deleteNotice(@RequestBody int no) {
        int result = service.deleteNotice(no);
        if (result != 0) {
            return new ResponseEntity<>("Notice Delete Success!", HttpStatus.OK);
        }
        return new ResponseEntity<>("Notice Delete Error!", HttpStatus.BAD_REQUEST);
    }
}
