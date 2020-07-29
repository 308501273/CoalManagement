package com.coal.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_assessment")
public class Assessment {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 公司id
     */
    @Column(name = "company_id")
    private Integer companyId;

    /**
     * 总集团给分
     */
    @Column(name = "headquarters_rating")
    private String headquartersRating;

    /**
     * 分公司评分
     */
    @Column(name = "branch_rating")
    private String branchRating;

    /**
     * 电厂评分
     */
    @Column(name = "power_rating")
    private String powerRating;

    /**
     * 评级
     */
    private String grade;


}