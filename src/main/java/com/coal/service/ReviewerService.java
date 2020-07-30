package com.coal.service;

import com.coal.common.exception.CoalException;
import com.coal.common.utils.ConstantClassFiled;
import com.coal.common.utils.ExceptionEnum;
import com.coal.mapper.NoticeMapper;
import com.coal.mapper.UserMapper;
import com.coal.pojo.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewerService {
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
        return noticeMapper.updateByPrimaryKeySelective(updatedNotice);
    }
}
