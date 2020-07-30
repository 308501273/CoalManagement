package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Notice;
import com.coal.service.PublisherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("publisher")
public class PublisherController {
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
            @RequestParam(value = "key", required = false) String key,
            Integer publisherId) {
        return ResponseEntity.ok(publisherService.getNotices(page,rows,sortBy,desc,key,publisherId));
    }
    @GetMapping("refused_notices")
    public ResponseEntity<PageResult<Notice>> getRefuseNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key,
            Integer publisherId) {
        return ResponseEntity.ok(publisherService.getRefuseNotices(page,rows,sortBy,desc,key,publisherId));
    }
    @DeleteMapping("/notice/{id}")
    public ResponseEntity<Integer> deleteNotice(@PathVariable("id") Integer id,Integer publisherId){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(publisherService.deleteNotice(id,publisherId));

    }
}
