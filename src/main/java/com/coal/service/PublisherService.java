package com.coal.service;


import com.coal.common.utils.PageResult;
import com.coal.pojo.Notice;

public interface PublisherService {

     Integer publishNotice(Notice notice) ;

     Integer updateNotice(Notice notice);

     PageResult<Notice> getNotices(Integer page, Integer rows, String sortBy, boolean desc, Integer status, String key, String value, Integer publisherId) ;

     PageResult<Notice> getRefuseNotices(Integer page, Integer rows, String sortBy, boolean desc, String key, Integer publisherId) ;

     Notice getNoticeById(Integer id);

     Integer deleteNotice(Integer id, Integer publisherId);

}
