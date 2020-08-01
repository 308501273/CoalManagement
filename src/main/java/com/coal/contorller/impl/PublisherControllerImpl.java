package com.coal.contorller.impl;

import com.coal.common.utils.PageResult;
import com.coal.contorller.PublisherController;
import com.coal.pojo.Notice;
import com.coal.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publisher")
public class PublisherControllerImpl implements PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping("notice")
    public ResponseEntity<Notice>getNoticeById(Integer id){
        return ResponseEntity.ok(publisherService.getNoticeById(id));
    }

    @PostMapping("notice")
    public ResponseEntity<Integer>publishNotice(Notice notice){
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.publishNotice(notice));
    }
    @PutMapping("notice")
    public ResponseEntity<Integer> updateNotice(Notice notice){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(publisherService.updateNotice(notice));
    }
    @GetMapping("notices")
    public ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "status",required = false)Integer status,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "value",required = false) String value,
            Integer publisherId) {
        return ResponseEntity.ok(publisherService.getNotices(page,rows,sortBy,desc,status,key,value,publisherId));
    }

    @DeleteMapping("/notice/{id}")
    public ResponseEntity<Integer> deleteNotice(@PathVariable("id") Integer id,Integer publisherId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(publisherService.deleteNotice(id,publisherId));
    }

}
