package com.coal.service;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.mapper.NoticeMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Notice;
import com.coal.pojo.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

public interface NoticeService {

     PageResult<Notice> getNotices(Integer page, Integer rows, String sortBy, boolean desc, String key) ;

     Integer addNotice(Notice notice);

     Notice getNoticeById(Integer id);

     Integer updateNoticeById(Notice notice) ;
}
