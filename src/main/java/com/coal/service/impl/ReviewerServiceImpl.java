package com.coal.service.impl;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.common.utils.PageResult;
import com.coal.contorller.ReviewerController;
import com.coal.mapper.NoticeMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Notice;
import com.coal.pojo.User;
import com.coal.service.ReviewerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class ReviewerServiceImpl implements ReviewerService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private UserMapper userMapper;

    public Integer updateNoticeStatus(Notice notice) {
        if (notice.getReviewerId() == null || notice.getId() == null || notice.getStatus() == null)
            throw new CoalException(ExceptionEnum.INVALID_REQUEST_PARAM);
        Notice updatedNotice = new Notice();
        updatedNotice.setReviewerId(notice.getReviewerId());
        updatedNotice.setId(notice.getId());
        updatedNotice.setStatus(notice.getStatus());
        if (updatedNotice.getStatus() == ConstantClassFiled.NOTICE_REFUSE_STATUS)
            updatedNotice.setRemark(notice.getRemark());
        notice=noticeMapper.selectByPrimaryKey(notice.getId());
        if(notice.getStatus()==updatedNotice.getStatus())
            throw new CoalException(ExceptionEnum.INVALID_REQUEST_PARAM);
        return noticeMapper.updateByPrimaryKeySelective(updatedNotice);
    }


    public PageResult<Notice> getNotices(Integer page, Integer rows, String sortBy, boolean desc,Integer status,String key, String value) {
        PageHelper.startPage(page, rows);

        Example example = new Example(Notice.class);

        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        if(StringUtils.isNotBlank(key)&&StringUtils.isNotBlank(value)){
            example.and(example.createCriteria().andLike(key,"%" + value + "%"));
        }
        if(status!=null)
            example.and(example.createCriteria().andEqualTo("status",status));
        example.and(example.createCriteria()
                .andEqualTo("status", ConstantClassFiled.NOTICE_REFUSE_STATUS)
        );
        List<Notice> noticeList = noticeMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(noticeList)) {
            throw new CoalException(ExceptionEnum.NOTICE_NOT_FOUND);
        }
        PageInfo<Notice> result = new PageInfo<>(noticeList);
        for (Notice notice : noticeList) {
            notice.setContent(null);
            User publisher = userMapper.selectByPrimaryKey(notice.getPublisherId());
            if (publisher != null)
                notice.setPublisher(publisher.getName());
            User reviewer = userMapper.selectByPrimaryKey(notice.getReviewerId());
            if (reviewer != null)
                notice.setReviewer(reviewer.getName());
        }
        return new PageResult<>(result.getTotal(), page, noticeList.size(), noticeList, rows);
    }
}
