package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Notice;
import com.coal.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("search")
    public ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key) {
        return ResponseEntity.ok(noticeService.getNotices(page,rows,sortBy,desc,key));
    }
    @PostMapping
    public ResponseEntity<Integer>addNotice(Notice notice){
        return ResponseEntity.status(HttpStatus.CREATED).body(noticeService.addNotice(notice));
    }
    @GetMapping
    public ResponseEntity<Notice>getNoticeById(Integer id){
        return ResponseEntity.ok(noticeService.getNoticeById(id));
    }
    @PutMapping
    public ResponseEntity<Integer>updateNoticeById(Notice notice){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(noticeService.updateNoticeById(notice));
    }
}
