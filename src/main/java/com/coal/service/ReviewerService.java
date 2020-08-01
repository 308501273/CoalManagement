package com.coal.service;

import com.coal.common.utils.PageResult;
import com.coal.pojo.Notice;

public interface ReviewerService {

     Integer updateNoticeStatus(Notice notice) ;

     PageResult<Notice> getNotices(Integer page, Integer rows, String sortBy, boolean desc,Integer status,String key, String value) ;
}
