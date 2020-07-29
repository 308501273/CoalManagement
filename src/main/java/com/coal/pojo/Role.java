package com.coal.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_role")
public class Role {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 角色
     */
    private String name;

    /**
     * 描述
     */
    private String remarks;


}