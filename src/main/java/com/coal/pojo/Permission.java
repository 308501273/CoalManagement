package com.coal.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tb_permission")
public class Permission {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 权限名
     */
    private String name;

    /**
     * 资源路径
     */
    private String url;

    /**
     * 描述
     */
    private String remarks;

}