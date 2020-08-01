package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Notice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PublisherController {

     ResponseEntity<Notice>getNoticeById(Integer id);

     ResponseEntity<Integer>publishNotice(Notice notice);

     ResponseEntity<Integer> updateNotice(Notice notice);

     ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "status",required = false)Integer status,
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "value",required = false) String value,
            Integer publisherId);

     ResponseEntity<Integer> deleteNotice(@PathVariable("id") Integer id,Integer publisherId);

}
