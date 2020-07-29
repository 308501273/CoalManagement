package com.coal.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "tb_audit")
public class Audit {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 采购单id
     */
    @Column(name = "purchase_id")
    private Integer purchaseId;

    /**
     * 审核人id
     */
    @Column(name = "reviewer_id")
    private Integer reviewerId;

    /**
     * 操作
     */
    private String operation;

    /**
     * 备注
     */
    private String remarks;

    private Date auditTime;
}