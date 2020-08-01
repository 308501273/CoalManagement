package com.coal.contorller;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Notice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface NoticeController {

     ResponseEntity<PageResult<Notice>> getNotices(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "desc", defaultValue = "false") boolean desc,
            @RequestParam(value = "key", required = false) String key);

     ResponseEntity<Integer>addNotice(Notice notice);

     ResponseEntity<Notice>getNoticeById(Integer id);

     ResponseEntity<Integer>updateNoticeById(Notice notice);
}
