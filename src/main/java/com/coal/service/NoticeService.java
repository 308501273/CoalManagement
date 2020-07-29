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

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private UserMapper userMapper;

    public PageResult<Notice> getNotices(Integer page, Integer rows, String sortBy, boolean desc, String key) {
        PageHelper.startPage(page, rows);

        Example example = new Example(Notice.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("title", "%" + key + "%")
                    .orLike("content", "%" + key + "%");
        }
        if (StringUtils.isNotBlank(sortBy)) {
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
;
        example.and(example.createCriteria().andEqualTo("status", ConstantClassFiled.NORMAL_STATUS));
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

    public Integer addNotice(Notice notice) {
        notice.setId(null);
        notice.setCreateTime(new Date());
        notice.setLastUpdateTime(new Date());
        notice.setReviewerId(null);
        notice.setStatus(ConstantClassFiled.APPROVAL_STATUS);
        if (notice.getPublisherId() == null)
            throw new CoalException(ExceptionEnum.INVALID_REQUEST_PARAM);
        return noticeMapper.insert(notice);
    }

    public Notice getNoticeById(Integer id) {
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        notice.setPublisher(userMapper.selectByPrimaryKey(notice.getPublisherId()).getName());
        return notice;
    }

    public Integer updateNoticeById(Notice notice) {
        notice.setStatus(ConstantClassFiled.APPROVAL_STATUS);
        return noticeMapper.updateByPrimaryKeySelective(notice);
    }
}
