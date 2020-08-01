package com.coal.contorller.impl;

import com.coal.common.utils.PageResult;
import com.coal.contorller.ReviewerController;
import com.coal.pojo.Notice;
import com.coal.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reviewer")
public class ReviewerControllerImpl implements ReviewerController {
    @Autowired
    private ReviewerService reviewerService;

    @GetMapping("notices")
    public ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", defaultValue = "last_update_time") String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "status",required = false)Integer status,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "value", required = false) String value) {
            return ResponseEntity.ok(reviewerService.getNotices(page,rows,sortBy,desc,status,key,value));
    }


    @PostMapping("notice")
    public ResponseEntity<Integer> udpateNoticeStatus(Notice notice) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewerService.updateNoticeStatus(notice));
    }
}
