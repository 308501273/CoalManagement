package com.coal.pojo;

import lombok.Data;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "tb_purchase_application")
public class PurchaseApplication {
    /**
     * 主键id
     */
    @Id
    private Integer id;

    /**
     * 申请单位
     */
    @Column(name = "applicant_company_Id")
    private Integer applicantCompanyId;
    /**
     * 申请单位名字
     */
    @Transient
    private String applicantCompanyName;

    /**
     * 申请人ID
     */
    @Column(name = "applicant_id")
    private Integer applicantId;
    /**
     * 申请人姓名
     */
    @Transient
    private String applicantName;

    /**
     * 签发人ID
     */
    @Column(name = "issuer_id")
    private Integer issuerId;
    /**
     * 签发人姓名
     */
    @Transient
    private String issuserName;
    /**
     * 申请日期
     */
    @Column(name = "application_date")
    private String applicationDate;

    /**
     * 缴费开始时间
     */
    @Column(name = "delivery_start_time")
    private Date deliveryStartTime;

    /**
     * 缴费截止时间
     */
    @Column(name = "delivery_end_time")
    private Date deliveryEndTime;

    /**
     * 煤种
     */
    @Column(name = "coal_variety")
    private Integer coalVarietyId;

    @Transient
    private String coalVarietyName;

    /**
     * 采购数量
     */
    private Double quantity;

    /**
     * 运输方式
     */
    private String transport;

    /**
     * 交货地点
     */
    private String destination;

    /**
     * 结算方式
     */
    private String settlement;

    /**
     * 验货方式
     */
    @Column(name = "Inspection")
    private String inspection;

    /**
     * 最高限价
     */
    @Column(name = "price_ceiling")
    private Double priceCeiling;

    /**
     * 最低限价
     */
    @Column(name = "floor_price")
    private Double floorPrice;

    /**
     * 结算方式
     */
    private String payment;

    /**
     * 收到基单位发热量
     */
    private Double calorific;

    /**
     * 收到基全硫
     */
    @Column(name = "base_total_sulfur")
    private Double baseTotalSulfur;

    /**
     * 全水分
     */
    @Column(name = "water_content")
    private Double waterContent;

    /**
     * 收到基灰分
     */
    @Column(name = "basis_ash")
    private Double basisAsh;

    /**
     * 收到基挥发分
     */
    @Column(name = "basis_volatile")
    private Double basisVolatile;

    /**
     * 空干基水分
     */
    @Column(name = "airdry_basis_moisture")
    private Double airdryBasisMoisture;

    /**
     * 空干基全硫
     */
    @Column(name = "airdry_basis_total_sulfur")
    private Double airdryBasisTotalSulfur;

    /**
     * 空干基挥发分
     */
    @Column(name = "airdry_basis_volatile")
    private Double airdryBasisVolatile;

    /**
     * 干基高位发热量
     */
    @Column(name = "dry_basis_calorific")
    private Double dryBasisCalorific;

    /**
     * 干基全硫
     */
    @Column(name = "dry_basis_total_sulfur")
    private Double dryBasisTotalSulfur;

    /**
     * 干基挥发分
     */
    @Column(name = "dry_basis_volatile")
    private Double dryBasisVolatile;

    /**
     * 粒度
     */
    private Double granularity;

    /**
     * 灰熔点
     */
    @Column(name = "ash_melting_point")
    private Double ashMeltingPoint;

    /**
     * 哈式可磨系数
     */
    @Column(name = "hardy_grindability_coefficient")
    private Double hardyGrindabilityCoefficient;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 状态
     */
    private Integer status;


}