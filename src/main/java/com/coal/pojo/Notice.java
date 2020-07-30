package com.coal.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "tb_notice")
public class Notice {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 发布人id
     */
    @Column(name = "publisher_id")
    private Integer publisherId;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 上次更新时间
     */
    @Column(name = "last_update_time")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 审核人id
     */
    @Column(name = "reviewer_id")
    private Integer reviewerId;
    /**
     * 审核备注
     */
    private String remark;
    /**
     * 发布人姓名
     */
    @Transient
    private String publisher;
    /**
     * 审核人姓名
     */
    @Transient
    private String reviewer;

}