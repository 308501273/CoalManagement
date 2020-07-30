package com.coal.contorller;

import com.coal.pojo.Notice;
import com.coal.service.ReviewerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("reviewer")
public class ReviewerController {
    @Autowired
    private ReviewerService reviewerService;

    @PostMapping("notice")
    public ResponseEntity<Integer> udpateNoticeStatus(Notice notice){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(reviewerService.updateNoticeStatus(notice));
    }
}
